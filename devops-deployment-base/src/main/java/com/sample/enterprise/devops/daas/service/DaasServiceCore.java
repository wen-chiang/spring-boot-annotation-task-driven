package com.sample.enterprise.devops.daas.service;

import com.sample.enterprise.devops.daas.annotation.deploy.CommonAccessible;
import com.sample.enterprise.devops.daas.annotation.promote.PromoteBase;
import com.sample.enterprise.devops.daas.annotation.routine.EvenFailed;
import com.sample.enterprise.devops.daas.annotation.routine.OnlyFailed;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.aware.JenkinsInfoAware;
import com.sample.enterprise.devops.daas.message.model.MessageDispatchHeader;
import com.sample.enterprise.devops.daas.message.session.ProcedureSessionBase;
import com.sample.enterprise.devops.daas.proc.DaasRoutine;
import org.slf4j.MDC;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public abstract class DaasServiceCore<T> {

    @Autowired(required = false)
    @Qualifier("routineSkipProp")
    private Properties routineSkipProp;


    public Properties getRoutineSkipProp() {
        return (routineSkipProp == null) ? new Properties() : routineSkipProp;
    }

    /**
     * Process endpoint general response message.
     *
     * @param annotation the annotation
     * @param request    the request
     * @param response   the response
     * @param message    the message
     * @param operation  the operation
     * @return the general response message
     */
    public abstract GeneralResponseMessage processEndpoint(Class<? extends Annotation> annotation, HttpServletRequest request, HttpServletResponse response, T message, String operation);

    /**
     * Process session review.
     *
     * @param response    the response
     * @param responseMsg the response msg
     * @param session     the session
     */
    protected void processSessionReview(HttpServletResponse response, GeneralResponseMessage responseMsg, ProcedureSessionBase session) {

        if (responseMsg.getStatus().equals(DeploymentConstants.PROGRESS_STATUS_KEY)) {
            responseMsg.setStatus(DeploymentConstants.SUCCESS_STATUS_KEY);
        }

        if (responseMsg.getStatus().equals(DeploymentConstants.SUCCESS_STATUS_KEY)) {
            responseMsg.setErrorDetail(null);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
        }

        if (responseMsg.getStatus().equals(DeploymentConstants.SUCCESS_STATUS_KEY) && session.getProperties().getProperty(DeploymentConstants.LOGIC_STATUS) != null) {
            responseMsg.setStatus(session.getProperties().getProperty(DeploymentConstants.LOGIC_STATUS));
        }
    }

    /**
     * Store lmk key info.
     *
     * @param request   the request
     * @param message   the message
     * @param operation the operation
     */
    protected void storeLMKKeyInfo(HttpServletRequest request, JenkinsInfoAware message, String operation) {
        storeLMKKeyInfo(request, message, operation, System.getProperty("OPENSHIFT_LIB_ENV"));
    }

    /**
     * Store lmk key info.
     *
     * @param request   the request
     * @param message   the message
     * @param operation the operation
     */
    protected void storeLMKKeyInfo(HttpServletRequest request, JenkinsInfoAware message, String operation, String deploymentInfraEnv) {

        String correlationId = (message.getHeader() != null && message.getHeader().getCorrelationId() != null) ? message.getHeader().getCorrelationId() : "Missing";
        String version = (message.getHeader() != null && message.getHeader().getApiVersion() != null) ? message.getHeader().getApiVersion() : "Missing";
        String jenkinsJobName = (message.getJenkinsInfo() != null && message.getJenkinsInfo().getJenkinsJobName() != null) ? message.getJenkinsInfo().getJenkinsJobName() : "Missing";
        MDC.put("correlationId", correlationId);
        MDC.put("appVersion", version);
        MDC.put("operationName", operation);
        MDC.put("sourceApplication", jenkinsJobName);

        MDC.put("applicationId", "DEVOPS");
        MDC.put("applicationType", "JAVA");
        MDC.put("hostIp", request.getLocalAddr());
        MDC.put("hostName", request.getLocalName());
        MDC.put("serviceType", deploymentInfraEnv);
    }

    /**
     * Start process routines.
     *
     * @param annotation  the annotation
     * @param responseMsg the response msg
     * @param header      the MessageDispatch Header
     */
    protected void startProcessRoutines(Class<? extends Annotation> annotation, GeneralResponseMessage responseMsg, MessageDispatchHeader header, ProcedureSessionBase session) {
        try {
            // only handle Publish annotation
            String promoteEnv = header.getPromoteEnv();
            List<DaasRoutine> routinesBeforeVersion = getProcessRoutines(promoteEnv);
            // version review filter
            List<DaasRoutine> routines = versionFilter(routinesBeforeVersion, header.getApiVersion());
            boolean previousSuccess = true;
            if (routines != null) {
                for (DaasRoutine theOne : routines) {
                    String routeSimpleName = AopProxyUtils.ultimateTargetClass(theOne).getSimpleName();
                    if (theOne.getClass().isAnnotationPresent(annotation)) {
                        List<String> procLogs = responseMsg.getProcessDetail().getProcessLog();
                        String skip = getRoutineSkipProp().getProperty("policy.routine.skip." + routeSimpleName);
                        // process Skip Part...
                        if (!StringUtils.isEmpty(skip) && skip.equalsIgnoreCase("true")) {
                            procLogs.add(routeSimpleName + " - " + "SKIP");
                        } else {
                            session.getProperties().remove(DeploymentConstants.ROUTINE_STATUS);
                            if (routineProcessRequired(previousSuccess, theOne, annotation)) {
                                theOne.process();
                                String status = responseMsg.getStatus();
                                if (status.equals(DeploymentConstants.FAIL_STATUS_KEY)) {
                                    previousSuccess = false;
                                    String finalStatus = statusForCurrentRoutine(session, status);
                                    procLogs.add(routeSimpleName + " - " + finalStatus);
                                } else {
                                    String finalStatus = statusForCurrentRoutine(session, "PASS");
                                    procLogs.add(routeSimpleName + " - " + finalStatus);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception exp) {
            ServiceExceptionHandler handler = new ServiceExceptionHandler();
            handler.updateCauseByException(responseMsg, exp);
        }
    }

    private String statusForCurrentRoutine(ProcedureSessionBase session, String previousStatus) {
        String finalStatus = session.getProperties().getProperty(DeploymentConstants.ROUTINE_STATUS);
        return (finalStatus == null) ? previousStatus : finalStatus;
    }

    private boolean routineProcessRequired(boolean stillSuccess, DaasRoutine routine, Class<? extends Annotation> annotation) {
        boolean ans = stillSuccess;
        boolean endPointHaveEvenFailed = (annotation.getDeclaredAnnotation(EvenFailed.class) != null);
        boolean routineWithEvenFailed = routine.getClass().isAnnotationPresent(EvenFailed.class);
        boolean routineWithOnlyFailed = routine.getClass().isAnnotationPresent(OnlyFailed.class);
        if (endPointHaveEvenFailed) {
            ans = true;
        } else if (routineWithEvenFailed) {
            ans = true;
        } else if (routineWithOnlyFailed) {
            ans = (!stillSuccess);
        }
        return ans;
    }


    /**
     * Gets process routines.
     *
     * @param deploymentTarget the deployment target
     * @return the process routines
     */
    @SuppressWarnings("unchecked")
    protected List<DaasRoutine> getProcessRoutines(String deploymentTarget) {

        List<DaasRoutine> ans = new ArrayList<>();
        Field[] allFields = this.getClass().getDeclaredFields();
        for (Field theOne : allFields) {
            Annotation[] annotations = theOne.getAnnotations();
            for (Annotation theAnn : annotations) {
                if (theAnn.annotationType().getTypeName().startsWith(PromoteBase.class.getPackage().getName() + ".Promote")) {
                    PromoteBase[] promote = theAnn.annotationType().getAnnotationsByType(PromoteBase.class);
                    if (promote != null && promote[0].name().equals(deploymentTarget)) {
                        ReflectionUtils.makeAccessible(theOne);
                        Object routines = null;
                        try {
                            routines = theOne.get(this);
                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }
                        if (routines instanceof List) {
                            ans = (List<DaasRoutine>) routines;
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    protected List<DaasRoutine> versionFilter(List<DaasRoutine> routines, String version) {

        if (version == null) {
            return routines;
        } else {
            List<DaasRoutine> ans = new ArrayList<>();
            Class<? extends Annotation> theAccessibleVersion = null;
            for (Annotation annotation : this.getClass().getAnnotations()) {
                Class<? extends Annotation> type = annotation.annotationType();
                if (type.getPackage() == CommonAccessible.class.getPackage()) {
                    theAccessibleVersion = (Class<? extends Annotation>) AnnotationUtils.getValue(annotation, "versionHolder");
                    break;
                }
            }
            for (DaasRoutine theOne : routines) {
                Annotation annAccessible = AnnotationUtils.findAnnotation(theOne.getClass(), theAccessibleVersion);
                if (annAccessible != null) {
                    Object versions = AnnotationUtils.getValue(annAccessible, "versions");
                    if (versions != null) {
                        String[] theVersions = (String[]) versions;
                        List<String> list = Arrays.asList(theVersions);
                        if (list.size() == 0 || list.contains(version)) {
                            ans.add(theOne);
                        }
                    }
                } else {
                    ans.add(theOne);
                }
            }
            return ans;
        }
    }
}

package com.sample.enterprise.devops.daas.sampleApp.service;

import com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAccessible;
import com.sample.enterprise.devops.daas.annotation.promote.PromoteDev;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.proc.DaasRoutine;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.service.DaasServiceCore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Showcase service for the annotation-driven orchestration pattern.
 *
 * <p>This is the concrete example used to prove that the core logic does not need to know every
 * individual routine ahead of time. Instead, the service asks the framework to resolve routines
 * by annotation and then executes them in the configured order.</p>
 */
@Service
@OpenshiftAccessible
public class SampleService extends DaasServiceCore<DeploymentMessageBase> {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Autowired(required = false)
    @OpenshiftAccessible
    @PromoteDev
    private List<DaasRoutine> manageSecretRoutines;


    @Override
    public GeneralResponseMessage processEndpoint(Class<? extends Annotation> annotation, HttpServletRequest request,
                                                  HttpServletResponse response, DeploymentMessageBase message, String operation) {
        // The sample flow deliberately keeps the execution path straightforward: initialise the
        // request/session state, discover routines by annotation metadata, and review the session.
        dataHolder.initialRequest(message);
        storeLMKKeyInfo(request, dataHolder.getRequestMsg(), operation);
        startProcessRoutines(annotation, dataHolder.getResponseMsg(), dataHolder.getRequestMsg().getHeader(), dataHolder.getSession());
        processSessionReview(response, dataHolder.getResponseMsg(), dataHolder.getSession());
        return dataHolder.getResponseMsg();
    }
}

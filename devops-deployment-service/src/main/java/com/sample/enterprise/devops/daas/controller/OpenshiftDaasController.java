package com.sample.enterprise.devops.daas.controller;

import com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAccessible;
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.controller.annotation.OpenshiftControllerValidator;
import com.sample.enterprise.devops.daas.message.*;
import com.sample.enterprise.devops.daas.message.model.validate.*;
import com.sample.enterprise.devops.daas.service.DaasServiceCore;
import com.sample.enterprise.devops.daas.util.ConvertUtils;
import com.sample.enterprise.devops.daas.util.Loggable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.sample.enterprise.devops.daas.constants.DeploymentConstants.*;
import static com.sample.enterprise.devops.daas.constants.MessageConstants.API_VERSION_V2;

@RestController
@EnableAutoConfiguration
@Tag(name = "Openshift", description = "Openshift Deployment API Set")
@SuppressWarnings("unchecked")
public class OpenshiftDaasController implements Loggable {

    @Autowired(required = false)
    @OpenshiftAccessible
    private DaasServiceCore service;

    /**
     * Promotion endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    // @Operation(summary = "Performs Credentials Application Cleanup", description = "Request Message : OpenshiftDeploymentMessage")
    // @RequestMapping(value = OPENSHIFT_PROMOTION_ENDPOINT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseBody
    @Operation(summary = "Performs Openshift Application Promote", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_PROMOTION_ENDPOINT)
    public GeneralResponseMessage promote(final HttpServletRequest request, final HttpServletResponse response,
                                          @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {
        return service.processEndpoint(Promote.class, request, response, message, DeploymentConstants.PROMOTION_KEY);
    }

    /**
     * Deployment endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift Deployment Preparation", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_DEPLOY_PREPARE_ENDPOINT)
    public GeneralResponseMessage deploymentPreparation(final HttpServletRequest request, final HttpServletResponse response,
                                                        @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {

        return service.processEndpoint(DeployPrepare.class, request, response, message, DeploymentConstants.PREPARE_KEY);
    }


    /**
     * export endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Export Openshift Deployment Configuration", description = "Request Message : OpenshiftExportMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_DEPLOY_PREPARE_EXPORT_ENDPOINT)
    public GeneralResponseMessage prepareExport(final HttpServletRequest request, final HttpServletResponse response,
                                                @OpenshiftControllerValidator @RequestBody final OpenshiftExportMessage message) {
        return service.processEndpoint(PrepareExport.class, request, response, ConvertUtils.toOpenShiftDeploymentMessage(message), DeploymentConstants.EXPORT_PREPARE_KEY);
    }


    /**
     * import endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Import Openshift Deployment Configuration", description = "Request Message : OpenshiftImportMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_DEPLOY_PREPARE_IMPORT_ENDPOINT)
    public GeneralResponseMessage prepareImport(final HttpServletRequest request, final HttpServletResponse response,
                                                @OpenshiftControllerValidator @RequestBody final OpenshiftImportMessage message) {
        return service.processEndpoint(PrepareImport.class, request, response, ConvertUtils.toOpenShiftDeploymentMessage(message), DeploymentConstants.IMPORT_PREPARE_KEY);
    }


    /**
     * deployment endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift Deployment Rollout", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_ROLLOUT_ENDPOINT)
    public GeneralResponseMessage deploymentRollout(final HttpServletRequest request, final HttpServletResponse response,
                                                    @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {

        return service.processEndpoint(DeployRollout.class, request, response, message, DeploymentConstants.ROLLOUT_KEY);
    }
	
	/**
     * rollback endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift Deployment Rollback", description = "Request Message : OpenshiftRollbackMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_ROLLBACK_ENDPOINT)
    public GeneralResponseMessage deploymentRollback(final HttpServletRequest request, final HttpServletResponse response,
                                                @OpenshiftControllerValidator @RequestBody final OpenshiftRollbackMessage message) {
        return service.processEndpoint(DeployRollback.class, request, response, ConvertUtils.toOpenShiftDeploymentMessage(message), DeploymentConstants.ROLLBACK_KEY);
    }

    /**
     * Build Image endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift Deployment Build Image", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_BUILD_IMAGE_ENDPOINT)
    public GeneralResponseMessage deploymentBuildImage(final HttpServletRequest request, final HttpServletResponse response,
                                                    @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {
        return service.processEndpoint(BuildImage.class, request, response, message, DeploymentConstants.BUILD_IMAGE_KEY);
    }


    /**
     * Status endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift Status", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_STATUS_ENDPOINT)
    public GeneralResponseMessage status(final HttpServletRequest request, final HttpServletResponse response,
                                         @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {

        return service.processEndpoint(Status.class, request, response, message, DeploymentConstants.STATUS_KEY);
    }

    /**
     * cleanup endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Application Cleanup", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CLEANUP_ENDPOINT)
    public GeneralResponseMessage cleanup(final HttpServletRequest request, final HttpServletResponse response,
                                          @OpenshiftControllerValidator @RequestBody final OpenshiftDeploymentMessage message) {

        return service.processEndpoint(CleanUp.class, request, response, message, DeploymentConstants.CLEANUP_KEY);
    }

    /**
     * config endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Deployment Configuration Validation", description = "Request Message : String")
    @DaasEndpoint(endpoint = OPENSHIFT_CONFIG_ENDPOINT)
    public GeneralResponseMessage config(final HttpServletRequest request, final HttpServletResponse response,
                                         @Validated({DeploymentConfigValidationRequired.class, ClientSecretRequired.class}) @RequestBody final OpenshiftDeploymentConfigValidationMessage message) {

        OpenshiftDeploymentMessage wrapper = new OpenshiftDeploymentMessage();
        wrapper.getHeader().setPromoteEnv("preview");
        wrapper.getHeader().setCorrelationId(UUID.randomUUID().toString());
        wrapper.getHeader().setApiVersion(message.getApiVersion());
        wrapper.getHeader().setDeployType("openshift");
        wrapper.setProjectConfig(message.getProjectConfig());
        wrapper.setProfileDetails(message.getProfileDetails());
        wrapper.getJenkinsInfo().setProjectName(message.getProjectName());
        wrapper.setAltZoneDeploymentType(message.getAltZoneDeploymentType());
        wrapper.getJenkinsInfo().setCluster(message.getCluster());
        return service.processEndpoint(Config.class, request, response, wrapper, DeploymentConstants.CONFIG_KEY);
    }

    /**
     * secret injection endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Application Cleanup", description = "Request Message : OpenshiftClientSecretMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CLIENT_SECRET_ENDPOINT)
    public GeneralResponseMessage clientSecret(final HttpServletRequest request, final HttpServletResponse response,
                                               @Validated({ClientSecretRequired.class}) @RequestBody final OpenshiftClientSecretMessage message) {

        return service.processEndpoint(InjectCredentails.class, request, response, createWrapper(message), DeploymentConstants.SECRET_INJECT_KEY);
    }

    /**
     * validateUser endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Application Cleanup", description = "Request Message : OpenshiftClientSecretMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CLIENT_VALIDATE_ENDPOINT)
    public GeneralResponseMessage clientValidate(final HttpServletRequest request, final HttpServletResponse response,
                                                 @Validated({ClientSecretRequired.class}) @RequestBody final OpenshiftClientSecretMessage message) {

        return service.processEndpoint(ValidateUser.class, request, response, createWrapper(message), DeploymentConstants.SECRET_VALIDATE_USER_KEY);
    }

    /**
     * validateUser endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Application Cleanup", description = "Request Message : OpenshiftClientSecretMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CLIENT_USER_VALIDATE_ENDPOINT)
    public GeneralResponseMessage clientUserValidate(final HttpServletRequest request, final HttpServletResponse response,
                                                     @Validated({ClientSecretRequired.class}) @RequestBody final OpenshiftClientSecretMessage message) {

        return service.processEndpoint(ValidateUser.class, request, response, createWrapper(message), DeploymentConstants.SECRET_VALIDATE_USER_KEY);
    }


    /**
     * deactivate endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Credentials Deployment Configuration Validation", description = "Request Message : OpenshiftClientSecretMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CLIENT_CONFIG_ENDPOINT)
    public GeneralResponseMessage clientConfig(final HttpServletRequest request, final HttpServletResponse response, @Validated({ClientSecretRequired.class}) @RequestBody final OpenshiftClientSecretMessage message) {

        return service.processEndpoint(ValidateCredentialsConfig.class, request, response, createWrapper(message), DeploymentConstants.SECRET_VALIDATE_CONFIG_KEY);
    }

    @Operation(summary = "Performs Openshift Scaling.", description = "Request Message : OpenshiftDeactivateDeploymentMessage")

    @DaasEndpoint(endpoint = OPENSHIFT_DEACTIVATE_ENDPOINT)
    public GeneralResponseMessage deactivateDeployment(final HttpServletRequest request,
                                                       final HttpServletResponse response,
                                                       @Validated({OpenshiftRequired.class}) @RequestBody final OpenshiftDeactivateDeploymentMessage message) {


        final OpenshiftDeploymentMessage convertedMessage = ConvertUtils.toOpenShiftDeploymentMessage(message);

        return service.processEndpoint(Deactivate.class, request, response, convertedMessage, DEACTIVATE_KEY);
    }


    private OpenshiftDeploymentMessage createWrapper(OpenshiftClientSecretMessage message) {
        OpenshiftDeploymentMessage wrapper = new OpenshiftDeploymentMessage();

        wrapper.setWrapObj(message);
        wrapper.getHeader().setCorrelationId(UUID.randomUUID().toString());
        wrapper.getHeader().setApiVersion(StringUtils.isEmpty(message.getApiVersion()) ? API_VERSION_V2 : message.getApiVersion());
        wrapper.getHeader().setDeployType("openshift");

        //carry incoming message in
        wrapper.getHeader().setPromoteEnv(message.getPromoteEnv());
        wrapper.getJenkinsInfo().setApplicationName(message.getProjectName());
        wrapper.getJenkinsInfo().setApplicationShortName(message.getProjectName());
        wrapper.setWrapObj(message);
        return wrapper;
    }

    /**
     * DryRun endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift DryRun Deployment", description = "Request Message : OpenshiftDeploymentMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_DRYRUN_ENDPOINT)
    public GeneralResponseMessage dryrun(final HttpServletRequest request, final HttpServletResponse response,
                                         @Validated({JenkinsInfoRequired.class, HeaderRequired.class}) @RequestBody final OpenshiftDeploymentMessage message) {

        return service.processEndpoint(DryRun.class, request, response, message, DeploymentConstants.DRYRUN_KEY);
    }

    /**
     * ValidateChangeRecord endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift change record validation", description = "Request Message : OpenshiftValidateChangeRecordMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_CHANGE_RECORD_VALIDATE_ENDPOINT)
    public GeneralResponseMessage validateChangeRecord(final HttpServletRequest request, final HttpServletResponse response,
                                         @Validated({JenkinsInfoRequired.class, HeaderRequired.class}) @RequestBody final OpenshiftValidateChangeRecordMessage message) {

        return service.processEndpoint(ValidateChangeRecord.class, request, response, ConvertUtils.toOpenShiftDeploymentMessage(message), DeploymentConstants.VALIDATE_CHANGE_RECORD_KEY);
    }

    /**
     * rebuildImage endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Openshift rebuild Image", description = "Request Message : OpenshiftRebuildMessage")
    @DaasEndpoint(endpoint = OPENSHIFT_REBUILD_IMAGE_ENDPOINT)
    public GeneralResponseMessage rebuildImage(final HttpServletRequest request, final HttpServletResponse response,
                                                       @Validated({ HeaderRequired.class ,OpenshiftRebuildImageRequired.class })
                                                       @RequestBody final OpenshiftRebuildImageMessage message) {

        return service.processEndpoint(RebuildImage.class, request, response, ConvertUtils.toOpenShiftDeploymentMessage(message), DeploymentConstants.REBUILD_IMAGE_KEY);
    }
}

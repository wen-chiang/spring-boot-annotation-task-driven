package com.sample.enterprise.devops.daas.controller;

import com.sample.enterprise.devops.daas.annotation.deploy.AnsibleAccessible;
import com.sample.enterprise.devops.daas.annotation.endpoint.CleanUp;
import com.sample.enterprise.devops.daas.annotation.endpoint.ActivateProfile;
import com.sample.enterprise.devops.daas.annotation.endpoint.Deployment;
import com.sample.enterprise.devops.daas.annotation.endpoint.GetLogs;
import com.sample.enterprise.devops.daas.annotation.endpoint.Status;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.controller.annotation.AnsibleControllerValidator;
import com.sample.enterprise.devops.daas.message.AnsibleCleanupMessage;
import com.sample.enterprise.devops.daas.message.validator.ActivateProfileMessage;
import com.sample.enterprise.devops.daas.message.AnsibleDeploymentMessage;
import com.sample.enterprise.devops.daas.message.AnsibleJobMessage;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.service.DaasServiceCore;
import com.sample.enterprise.devops.daas.util.Loggable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.sample.enterprise.devops.daas.constants.DeploymentConstants.*;

@RestController
@EnableAutoConfiguration
@Tag(name = "Ansible", description = "Ansible Deployment API Set")
@SuppressWarnings("unchecked")
public class AnsibleDaasController implements Loggable {

    @Autowired(required = false)
    @AnsibleAccessible
    private DaasServiceCore service;

    /**
     * Deployment endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Ansible Deployment", description = "Request Message : AnsibleDeploymentMessage")
    @DaasEndpoint(endpoint = ANSIBLE_DEPLOYMENT_ENDPOINT)
    public GeneralResponseMessage deployment(final HttpServletRequest request, final HttpServletResponse response,
                                             @AnsibleControllerValidator @RequestBody final AnsibleDeploymentMessage message) {

        return service.processEndpoint(Deployment.class, request, response, message, DeploymentConstants.DEPLOYMENT_KEY);
    }


    /**
     * Status endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Ansible Status", description = "Request Message : AnsibleJobMessage")
    @DaasEndpoint(endpoint = ANSIBLE_STATUS_ENDPOINT)
    public GeneralResponseMessage status(final HttpServletRequest request, final HttpServletResponse response,
                                         @AnsibleControllerValidator @RequestBody final AnsibleJobMessage message) {

        return service.processEndpoint(Status.class, request, response, message, DeploymentConstants.STATUS_KEY);
    }

    /**
     * Cleanup endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Ansible Application Cleanup", description = "Request Message : AnsibleCleanupMessage")
    @DaasEndpoint(endpoint = ANSIBLE_CLEANUP_ENDPOINT)
    public GeneralResponseMessage cleanup(final HttpServletRequest request, final HttpServletResponse response,
                                          @AnsibleControllerValidator @RequestBody final AnsibleCleanupMessage message) {

        return service.processEndpoint(CleanUp.class, request, response, message, CLEANUP_KEY);
    }

    /**
     * Get logs endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Gets Ansible Job Logs", description = "Request Message : AnsibleJobMessage")
    @DaasEndpoint(endpoint = ANSIBLE_GET_LOGS_ENDPOINT)
    public GeneralResponseMessage getLogs(final HttpServletRequest request, final HttpServletResponse response,
                                          @AnsibleControllerValidator @RequestBody final AnsibleJobMessage message) {

        return service.processEndpoint(GetLogs.class, request, response, message, GET_LOGS_KEY);
    }

    /**
     * Activate Profile endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Ansible Activate Profile", description = "Request Message : ActivateProfileMessage")
    @DaasEndpoint(endpoint = ANSIBLE_ACTIVATE_PROFILE_ENDPOINT)
    public GeneralResponseMessage activateProfile(final HttpServletRequest request, final HttpServletResponse response,
                                          @AnsibleControllerValidator @RequestBody final ActivateProfileMessage message) {

        return service.processEndpoint(ActivateProfile.class, request, response, message, ACTIVATE_PROFILE_KEY);
    }
}

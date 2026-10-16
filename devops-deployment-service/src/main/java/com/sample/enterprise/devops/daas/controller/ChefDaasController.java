package com.sample.enterprise.devops.daas.controller;

import com.sample.enterprise.devops.daas.annotation.deploy.ChefAccessible;
import com.sample.enterprise.devops.daas.annotation.endpoint.CleanUp;
import com.sample.enterprise.devops.daas.annotation.endpoint.Deployment;
import com.sample.enterprise.devops.daas.annotation.endpoint.Promote;
import com.sample.enterprise.devops.daas.annotation.endpoint.Status;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.controller.annotation.ChefControllerValidator;
import com.sample.enterprise.devops.daas.message.ChefDeploymentMessage;
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
@Tag(name = "Chef", description = "Chef Deployment API Set")
@SuppressWarnings("unchecked")
public class ChefDaasController implements Loggable {

    @Autowired(required = false)
    @ChefAccessible
    private DaasServiceCore service;

    /**
     * Promotion endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Chef Application Cleanup", description = "Request Message : ChefDeploymentMessage")
    @DaasEndpoint(endpoint = CHEF_PROMOTION_ENDPOINT)
    public GeneralResponseMessage promote(final HttpServletRequest request, final HttpServletResponse response,
                                          @ChefControllerValidator @RequestBody final ChefDeploymentMessage message) {

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
    @Operation(summary = "Performs Chef Deployment", description = "Request Message : ChefDeploymentMessage")
    @DaasEndpoint(endpoint = CHEF_DEPLOYMENT_ENDPOINT)
    public GeneralResponseMessage deployment(final HttpServletRequest request, final HttpServletResponse response,
                                             @ChefControllerValidator @RequestBody final ChefDeploymentMessage message) {

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
    @Operation(summary = "Performs Chef Status", description = "Request Message : ChefDeploymentMessage")
    @DaasEndpoint(endpoint = CHEF_STATUS_ENDPOINT)
    public GeneralResponseMessage status(final HttpServletRequest request, final HttpServletResponse response,
                                         @ChefControllerValidator @RequestBody final ChefDeploymentMessage message) {

        return service.processEndpoint(Status.class, request, response, message, DeploymentConstants.STATUS_KEY);
    }

    /**
     * Promotion endpoint.
     *
     * @param request  the request
     * @param response the response
     * @param message  the message
     * @return the general response message
     */
    @Operation(summary = "Performs Chef Application Cleanup", description = "Request Message : ChefDeploymentMessage")
    @DaasEndpoint(endpoint = CHEF_CLEANUP_ENDPOINT)
    public GeneralResponseMessage cleanup(final HttpServletRequest request, final HttpServletResponse response,
                                          @ChefControllerValidator @RequestBody final ChefDeploymentMessage message) {

        return service.processEndpoint(CleanUp.class, request, response, message, DeploymentConstants.CLEANUP_KEY);
    }

}

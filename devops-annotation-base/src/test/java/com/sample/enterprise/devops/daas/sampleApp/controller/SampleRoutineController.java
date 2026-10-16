package com.sample.enterprise.devops.daas.sampleApp.controller;

import com.sample.enterprise.devops.daas.annotation.endpoint.Publish;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.MessageDispatchHeader;
import com.sample.enterprise.devops.daas.sampleApp.service.SampleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Thin HTTP trigger for the sample application.
 *
 * <p>This controller exists only to demonstrate the intended flow: an incoming request reaches a
 * controller, which delegates to the service layer, which then resolves annotated routines and runs
 * them through {@link com.sample.enterprise.devops.daas.service.DaasServiceCore}.</p>
 */
@RestController
@RequestMapping("/sample")
public class SampleRoutineController {

    @Autowired
    private SampleService sampleService;

    @PostMapping("/publish")
    public GeneralResponseMessage publish(@RequestBody(required = false) DeploymentMessageBase message,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        if (message == null) {
            message = new DeploymentMessageBase();
        }
        if (message.getHeader() == null) {
            message.setHeader(new MessageDispatchHeader());
        }

        MessageDispatchHeader header = message.getHeader();
        header.setDeployType("openshift");
        header.setApiVersion("v2");
        header.setPromoteEnv("dev");
        if (header.getCorrelationId() == null || header.getCorrelationId().trim().isEmpty()) {
            header.setCorrelationId("sample-controller-" + UUID.randomUUID());
        }

        return sampleService.processEndpoint(Publish.class, request, response, message, "Publish");
    }
}

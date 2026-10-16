package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.OpenshiftRequired;

import jakarta.validation.constraints.NotEmpty;

public class OpenshiftImportMessage extends DeploymentMessageBase {

    @NotEmpty(groups = {OpenshiftRequired.class})
    private String deploymentConfig;

    public String getDeploymentConfig() {
        return deploymentConfig;
    }

    public void setDeploymentConfig(String deploymentConfig) {
        this.deploymentConfig = deploymentConfig;
    }
}

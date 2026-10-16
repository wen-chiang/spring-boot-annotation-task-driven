/*
 *
 */
package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.AnsibleRequired;

import jakarta.validation.constraints.NotNull;

public class AnsibleCleanupMessage extends DeploymentMessageBase {

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleNodes;

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleJobName;

    public String getAnsibleNodes() {
        return ansibleNodes;
    }

    public void setAnsibleNodes(String ansibleNodes) {
        this.ansibleNodes = ansibleNodes;
    }

    public String getAnsibleJobName() {
        return ansibleJobName;
    }

    public void setAnsibleJobName(String ansibleJobName) {
        this.ansibleJobName = ansibleJobName;
    }

    @Override
    public String toString() {
        return "AnsibleCleanupMessage{" +
                "ansibleNodes='" + ansibleNodes + '\'' +
                ", ansibleJobName='" + ansibleJobName + '\'' +
                '}';
    }
}

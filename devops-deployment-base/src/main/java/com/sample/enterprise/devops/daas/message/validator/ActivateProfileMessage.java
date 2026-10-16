/*
 *
 */
package com.sample.enterprise.devops.daas.message.validator;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.AnsibleRequired;

import jakarta.validation.constraints.NotNull;

public class ActivateProfileMessage extends DeploymentMessageBase {

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleNodes;

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleJobName;

    @NotNull(groups = {AnsibleRequired.class})
    private String activate;

    @NotNull(groups = {AnsibleRequired.class})
    private String origProfile;

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

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }

    public String getOrigProfile() {
        return origProfile;
    }

    public void setOrigProfile(String origProfile) {
        this.origProfile = origProfile;
    }

    @Override
    public String toString() {
        return "ActivateProfileMessage{" +
                "ansibleNodes='" + ansibleNodes + '\'' +
                ", ansibleJobName='" + ansibleJobName + '\'' +
                ", activate='" + activate + '\'' +
                ", origProfile='" + origProfile + '\'' +
                '}';
    }
}

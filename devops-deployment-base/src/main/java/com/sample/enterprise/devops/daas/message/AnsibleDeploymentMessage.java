/*
 *
 */
package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.AnsibleRequired;

import jakarta.validation.constraints.NotNull;

public class AnsibleDeploymentMessage extends DeploymentMessageBase {

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleNodes;

    @NotNull(groups = {AnsibleRequired.class})
    private String tarUrl;

    @NotNull(groups = {AnsibleRequired.class})
    private String configFileName;

    @NotNull(groups = {AnsibleRequired.class})
    private String ansibleJobName;

    private String activate = "true";

    private String rollback;

    private String origProfile;

    public String getTarUrl() {
        return tarUrl;
    }

    public void setTarUrl(String tarUrl) {
        this.tarUrl = tarUrl;
    }

    public String getAnsibleNodes() {
        return ansibleNodes;
    }

    public void setAnsibleNodes(String ansibleNodes) {
        this.ansibleNodes = ansibleNodes;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getAnsibleJobName() {
        return ansibleJobName;
    }

    public void setAnsibleJobName(String ansibleJobName) {
        this.ansibleJobName = ansibleJobName;
    }

    public String getActivate() { return activate; }

    public void setActivate(String activate) { this.activate = activate; }

    public String getRollback() { return rollback; }

    public void setRollback(String rollback) { this.rollback = rollback; }

    public String getOrigProfile() {
        // Temp fix for compatibility with 5.0.2. To be removed later.
        if(origProfile == null) {
            return getHeader().getPromoteEnv();
        }
        return origProfile;
    }

    public void setOrigProfile(String origProfile) {
        this.origProfile = origProfile;
    }

    @Override
    public String toString() {
        return "AnsibleDeploymentMessage{" +
                "ansibleNodes='" + ansibleNodes + '\'' +
                ", tarUrl='" + tarUrl + '\'' +
                ", configFileName='" + configFileName + '\'' +
                ", ansibleJobName='" + ansibleJobName + '\'' +
                ", activate='" + activate + '\'' +
                ", rollback='" + rollback + '\'' +
                ", origProfile='" + origProfile + '\'' +
                '}';
    }
}

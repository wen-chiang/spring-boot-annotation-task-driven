package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.OpenshiftRebuildImageRequired;

import jakarta.validation.constraints.NotEmpty;


public class OpenshiftRebuildImageMessage extends DeploymentMessageBase {

    @NotEmpty(groups= {OpenshiftRebuildImageRequired.class})
    private String tarUrl;

    private boolean skipImageExistsCheck=false;

    public boolean isSkipImageExistsCheck() {
        return skipImageExistsCheck;
    }

    public void setSkipImageExistsCheck(boolean skipImageExistsCheck) {
        this.skipImageExistsCheck = skipImageExistsCheck;
    }

    public String getTarUrl() {
        return tarUrl;
    }

    public void setTarUrl(String tarUrl) {
        this.tarUrl = tarUrl;
    }

    @Override
    public String toString() {
        return "OpenshiftRebuildImageMessage{" +
                "tarUrl='" + tarUrl + '\'' +
                ", skipImageExistsCheck=" + skipImageExistsCheck +
                '}';
    }
}

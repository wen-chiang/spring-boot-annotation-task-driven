/*
 *
 */
package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.AnsibleRequired;

import jakarta.validation.constraints.NotNull;

public class AnsibleJobMessage extends DeploymentMessageBase {

    @NotNull(groups = {AnsibleRequired.class})
    private String jobID;

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    @Override
    public String toString() {
        return "AnsibleJobMessage{" +
                "jobID='" + jobID + '\'' +
                '}';
    }

}

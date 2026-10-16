/*
 *
 */
package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.HeaderRequired;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChefDeploymentMessage extends DeploymentMessageBase {

    @NotNull(groups = {HeaderRequired.class})
    private String chefNodes;
    @NotEmpty(groups = {HeaderRequired.class})
    private String deploymentDirectory;
    @NotEmpty(groups = {HeaderRequired.class})
    private String tarUrl;


    public String getTarUrl() {
        return tarUrl;
    }

    public void setTarUrl(String tarUrl) {
        this.tarUrl = tarUrl;
    }

    /**
     * Gets the chef nodes.
     *
     * @return the chef nodes
     */
    public String getChefNodes() {
        return chefNodes;
    }

    /**
     * Sets the chef nodes.
     *
     * @param chefNodes the new chef nodes
     */
    public void setChefNodes(String chefNodes) {
        this.chefNodes = chefNodes;
    }

    /**
     * Gets the deployment directory.
     *
     * @return the deployment  directory
     */
    public String getDeploymentDirectory() {
        return deploymentDirectory;
    }

    /**
     * Sets the deployment directory.
     *
     * @param deploymentDirectory the new deployment directory
     */
    public void setDeploymentDirectory(String deploymentDirectory) {
        this.deploymentDirectory = deploymentDirectory;
    }

    @Override
    public String toString() {
        return "ChefDeploymentMessage{" + "chefNodes='" + chefNodes + '\'' + ", deploymentDirectory='" + deploymentDirectory + '\'' + '}';
    }
}

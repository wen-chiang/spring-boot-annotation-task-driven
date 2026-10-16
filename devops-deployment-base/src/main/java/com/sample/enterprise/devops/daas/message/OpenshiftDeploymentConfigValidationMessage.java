package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.validate.ClientSecretRequired;
import com.sample.enterprise.devops.daas.message.model.validate.DeploymentConfigValidationRequired;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.sample.enterprise.devops.daas.constants.MessageConstants.API_VERSION_V2;
import static com.sample.enterprise.devops.daas.constants.MessageConstants.ALTZONE_DEPLOYMENT_TYPE_ACTIVE_PASSIVE;

public class OpenshiftDeploymentConfigValidationMessage {

    @NotEmpty(groups = {DeploymentConfigValidationRequired.class})
    private String projectConfig;

    @NotEmpty(groups = {DeploymentConfigValidationRequired.class})
    @Size(min = 1, max = 100, groups = {DeploymentConfigValidationRequired.class})
    private String projectName;

    @NotEmpty(groups = {DeploymentConfigValidationRequired.class})
    private Map<String, Set<String>> profileDetails = new HashMap<>();

    @Pattern.List({
            @Pattern(regexp = "^$|v2", groups = {ClientSecretRequired.class})
    })
    private String apiVersion = API_VERSION_V2;

    @Pattern.List({
            @Pattern(regexp = "^$|ALTZONE_ACTIVE_PASSIVE|ALTZONE_ACTIVE_ACTIVE")
    })
    private String altZoneDeploymentType = ALTZONE_DEPLOYMENT_TYPE_ACTIVE_PASSIVE;

    private String cluster;

    public String getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(String projectConfig) {
        this.projectConfig = projectConfig;
    }

    public Map<String, Set<String>> getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(Map<String, Set<String>> profileDetails) {
        this.profileDetails = profileDetails;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAltZoneDeploymentType() {
        return altZoneDeploymentType;
    }

    public void setAltZoneDeploymentType(String altZoneDeploymentType) {
            this.altZoneDeploymentType = altZoneDeploymentType;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}

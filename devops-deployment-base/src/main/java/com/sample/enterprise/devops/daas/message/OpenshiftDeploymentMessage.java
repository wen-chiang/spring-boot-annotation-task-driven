package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.validate.OpenshiftRequired;
import com.sample.enterprise.devops.daas.message.validator.OpenshiftZoneApiVersionMatch;

import jakarta.validation.constraints.NotEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.sample.enterprise.devops.daas.constants.MessageConstants.ALTZONE_DEPLOYMENT_TYPE_ACTIVE_PASSIVE;

@OpenshiftZoneApiVersionMatch(groups = {OpenshiftRequired.class})
public class OpenshiftDeploymentMessage extends DeploymentMessageBase {

    @NotEmpty
    private String tarUrl;

    // TODO - added for backward compatability to service old bundles
    private String configFileName = "config.yml";

    private Map<String, String> provisionProperties = new HashMap<>();

    private String projectConfig;

    private Object wrapObj;

    private Map<String, Set<String>> profileDetails = new HashMap<>();

    private String altZoneDeploymentType = ALTZONE_DEPLOYMENT_TYPE_ACTIVE_PASSIVE;

    /**
     * Gets tar url.
     *
     * @return the tar url
     */
    public String getTarUrl() {
        return tarUrl;
    }

    /**
     * Sets tar url.
     *
     * @param tarUrl the tar url
     */
    public void setTarUrl(String tarUrl) {
        this.tarUrl = tarUrl;
    }

    public String getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(String getProjectConfig) {
        this.projectConfig = getProjectConfig;
    }

    public Object getWrapObj() {
        return wrapObj;
    }

    public void setWrapObj(Object wrapObj) {
        this.wrapObj = wrapObj;
    }

    public Map<String, String> getProvisionProperties() {
        return provisionProperties;
    }

    public void setProvisionProperties(Map<String, String> provisionProperties) {
        this.provisionProperties = provisionProperties;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public Map<String, Set<String>> getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(Map<String, Set<String>> profileDetails) {
        this.profileDetails = profileDetails;
    }

    public String getAltZoneDeploymentType() {
        return altZoneDeploymentType;
    }

    public void setAltZoneDeploymentType(String altZoneDeploymentType) {
            this.altZoneDeploymentType = altZoneDeploymentType;
    }

    @Override
    public String toString() {
        return "OpenshiftDeploymentMessage{" +
                "tarUrl='" + tarUrl + '\'' +
                ", configFileName='" + configFileName + '\'' +
                ", provisionProperties=" + provisionProperties +
                ", projectConfig='" + projectConfig + '\'' +
                ", wrapObj=" + wrapObj +
                '}';
    }
}

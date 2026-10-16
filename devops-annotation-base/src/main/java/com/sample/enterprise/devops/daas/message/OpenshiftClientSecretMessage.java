package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.validate.ClientSecretRequired;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.sample.enterprise.devops.daas.constants.MessageConstants.API_VERSION_V2;


public class OpenshiftClientSecretMessage {

    @Size(min = 1, max = 100, groups = {ClientSecretRequired.class})
    private String projectName;

    @Size(min = 1, max = 8, groups = {ClientSecretRequired.class})
    private String userName;

    @Size(min = 1, max = 100, groups = {ClientSecretRequired.class})
    private String gitHubOrgCode;

    @Size(min = 1, groups = {ClientSecretRequired.class})
    private String config;

    @Pattern.List({
            @Pattern(regexp = "^$|v2", groups = {ClientSecretRequired.class})
    })
    private String apiVersion = API_VERSION_V2;

    @Pattern.List({
            @Pattern(regexp = "ci|dev|trial|test|uat|qa|prod|prod_.*", groups = {ClientSecretRequired.class}),
    })
    private String promoteEnv = null; // UAT, Prod, ....

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGitHubOrgCode() {
        return gitHubOrgCode;
    }

    public void setGitHubOrgCode(String gitHubOrgCode) {
        this.gitHubOrgCode = gitHubOrgCode;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }


    public String getPromoteEnv() {
        return promoteEnv;
    }

    public void setPromoteEnv(String promoteEnv) {
        this.promoteEnv = promoteEnv;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}

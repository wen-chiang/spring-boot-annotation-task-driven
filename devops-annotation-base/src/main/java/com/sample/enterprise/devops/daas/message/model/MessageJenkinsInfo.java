package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.message.model.validate.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class MessageJenkinsInfo {

    private boolean multiZoneConfigured;

    @NotEmpty(groups = {JenkinsInfoRequired.class , OpenshiftRebuildImageRequired.class})
    @Size(min = 1, max = 100, groups = {JenkinsInfoRequired.class, OpenshiftRebuildImageRequired.class})
    private String jenkinsJobName;

    private String deployJobName;

    @NotEmpty(groups = {JenkinsInfoRequired.class , OpenshiftRebuildImageRequired.class})
    @Min(value = 0, groups = {JenkinsInfoRequired.class, OpenshiftRebuildImageRequired.class})
    @Max(value = Long.MAX_VALUE)
    private String buildNumber;

    private String deployBuildNumber;


    @NotEmpty(groups = {OpenshiftRequired.class})
    @Size(min = 1, max = 100, groups = {OpenshiftRequired.class})
    protected String projectName;

    @NotEmpty(groups = {OpenshiftRequired.class, OpenshiftRebuildImageRequired.class})
    private String gitOrgName;


    @NotEmpty(groups = {JenkinsInfoRequired.class})
    @Size(min = 1, max = 100, groups = {JenkinsInfoRequired.class})
    private String applicationName;

    @NotEmpty(groups = {JenkinsInfoRequired.class})
    @Size(min = 1, max = 100, groups = {JenkinsInfoRequired.class})
    private String applicationVersion;

    @NotEmpty(groups = {JenkinsInfoRequired.class})
    @Size(min = 1, max = 100, groups = {JenkinsInfoRequired.class})
    private String applicationShortName;

    private String remedyId;

    @NotNull(groups = {JenkinsInfoRequired.class})
    @Size(min = 1, max = 100, groups = {OpenshiftRequired.class, AnsibleRequired.class, ChefRequired.class})
    @Pattern(regexp = "", groups = {EnterpriseRequired.class})
    private String branchName;

    @NotEmpty(groups = {JenkinsInfoRequired.class, OpenshiftRebuildImageRequired.class })
    @Size(min = 1, max = 100, groups = {JenkinsInfoRequired.class, OpenshiftRebuildImageRequired.class })
    private String releaseVersion;

    @NotEmpty(groups = {ClientSecretRequired.class})
    @Size(min = 1, max = 100, groups = {ClientSecretRequired.class})
    private String userId;

    @NotEmpty(groups = {ClientSecretRequired.class})
    @Size(min = 1, max = 100, groups = {ClientSecretRequired.class})
    private String gitHubOrgCode;

    @NotEmpty(groups = {ClientSecretRequired.class})
    @Pattern(regexp = "openshift|vault", groups = {ClientSecretRequired.class})
    private String validateServiceType;

    private boolean activate = true;

    @Pattern(regexp = "^$|TOP01|TOP02|FTW01|DEV01|LAB02|POC01", groups = {OpenshiftRequired.class})
    private String zone = "";

    private String trouxAppId;

    @Valid
    private VCSDetails vcsDetails;
    
    private String cluster;

    /**
     * Gets the jenkins job name.
     *
     * @return the jenkins job name
     */
    public String getJenkinsJobName() {
        return jenkinsJobName;
    }

    /**
     * Sets the jenkins job name.
     *
     * @param jenkinsJobName the new jenkins job name
     */
    public void setJenkinsJobName(String jenkinsJobName) {
        this.jenkinsJobName = jenkinsJobName;
    }


    public String getDeployJobName() {
        return StringUtils.isNotEmpty(deployJobName) ? deployJobName : jenkinsJobName;
    }

    public void setDeployJobName(String deployJobName) {
        this.deployJobName = deployJobName;
    }

    public String getDeployBuildNumber() {
        return StringUtils.isNotEmpty(deployBuildNumber) ? deployBuildNumber : buildNumber;
    }

    public void setDeployBuildNumber(String deployBuildNumber) {
        this.deployBuildNumber = deployBuildNumber;
    }

    /**
     * Gets the builds the number.
     *
     * @return the builds the number
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * Sets the builds the number.
     *
     * @param buildNumber the new builds the number
     */
    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    /**
     * Gets the application name.
     *
     * @return the application name
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the application name.
     *
     * @param applicationName the new application name
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Gets the application version.
     *
     * @return application version which will be deployed
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Sets the application version.
     *
     * @param applicationVersion the version of application to be deployed
     */
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    /**
     * Gets the application short name.
     *
     * @return the application short name
     */
    public String getApplicationShortName() {
        return applicationShortName;
    }

    /**
     * Sets the application short name.
     *
     * @param applicationShortName the new application short name
     */
    public void setApplicationShortName(String applicationShortName) {
        this.applicationShortName = applicationShortName;
    }

    /**
     * Gets the remedy id.
     *
     * @return the remedy id
     */
    public String getRemedyId() {
        return remedyId;
    }

    /**
     * Sets the remedy id.
     *
     * @param remedyId the new remedy id
     */
    public void setRemedyId(String remedyId) {
        this.remedyId = remedyId;
    }

    /**
     * Get  version control system details
     *
     * @return version control system details
     */
    public VCSDetails getVcsDetails() {
        return vcsDetails;
    }

    /**
     * Set  version control system details
     *
     * @param vcsDetails version control system details
     */
    public void setVcsDetails(VCSDetails vcsDetails) {
        this.vcsDetails = vcsDetails;
    }


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getGitHubOrgCode() {
        return gitHubOrgCode;
    }

    public void setGitHubOrgCode(String gitHubOrgCode) {
        this.gitHubOrgCode = gitHubOrgCode;
    }

    public String getValidateServiceType() {
        return validateServiceType;
    }

    public void setValidateServiceType(String validateServiceType) {
        this.validateServiceType = validateServiceType;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets Multizone configuration flag..
     *
     * @param multiZoneConfigured is multizone configured for the project.
     */
    public void setMultiZoneConfigured(boolean multiZoneConfigured) {
        this.multiZoneConfigured = multiZoneConfigured;
    }

    /**
     * Gets multizone configuration flag.
     *
     * @return the multiZone configuration flag.
     */
    public boolean isMultiZoneConfigured() {
        return multiZoneConfigured;
    }

    public String getGitOrgName() {
        return gitOrgName;
    }

    public void setGitOrgName(String gitOrgName) {
        this.gitOrgName = gitOrgName;
    }

    public String getTrouxAppId() {
        return trouxAppId;
    }

    public void setTrouxAppId(String trouxAppId) {
        this.trouxAppId = trouxAppId;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}

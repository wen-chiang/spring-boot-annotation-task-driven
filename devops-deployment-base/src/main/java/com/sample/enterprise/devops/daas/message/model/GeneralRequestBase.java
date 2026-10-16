package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.message.model.validate.OpenshiftRequired;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

public class GeneralRequestBase implements Serializable {

    @NotEmpty(groups = {OpenshiftRequired.class})
    private String projectName;

    @NotEmpty(groups = {OpenshiftRequired.class})
    private String applicationName;

    @NotEmpty(groups = {OpenshiftRequired.class})
    @Pattern.List({
            @Pattern(regexp = "ci|dev|trial|test|uat|qa|prod|prod_*", groups = {OpenshiftRequired.class}),
    })
    private String profile;

    @Pattern(regexp = "^$|TOP01|TOP02|FTW01", groups = {OpenshiftRequired.class})
    private String zone;

    private String cluster;

    public GeneralRequestBase() {
    }

    public final GeneralRequestBase setProjectName(final String projectName) {
        this.projectName = projectName;
        return this;
    }

    public final String getProjectName() {
        return projectName;
    }

    public final GeneralRequestBase setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public final String getApplicationName() {
        return applicationName;
    }

    public final GeneralRequestBase setProfile(final String profile) {
        this.profile = profile;
        return this;
    }

    public final String getProfile() {
        return profile;
    }

    public final GeneralRequestBase setZone(final String zone) {
        this.zone = zone;
        return this;
    }

    public final String getZone() {
        return zone;
    }

    public final GeneralRequestBase setCluster(final String cluster) {
        this.cluster = cluster;
        return this;
    }

    public final String getCluster() {
        return cluster;
    }
}

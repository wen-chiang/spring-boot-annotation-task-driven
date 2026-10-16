package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.message.model.validate.VCSRequired;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VCSDetails {

    @NotNull(groups = {VCSRequired.class})
    @Size(min = 1, max = 100, groups = {VCSRequired.class})
    @Pattern(regexp = "GIT|SVN", groups = {VCSRequired.class})
    private String vcsType;

    @NotNull(groups = {VCSRequired.class})
    @Size(min = 1, max = 150, groups = {VCSRequired.class})
    private String url;

    @NotNull(groups = {VCSRequired.class})
    @Size(min = 1, max = 100, groups = {VCSRequired.class})
    private String branch;

    @NotNull(groups = {VCSRequired.class})
    @Size(min = 1, max = 100, groups = {VCSRequired.class})
    private String commitId;

    /**
     * Get  project VCS url
     *
     * @return the project url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set project VCS url
     *
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get branch name
     *
     * @return the branch name
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Set branch name
     *
     * @param branch the new branch name
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Get commit Id
     *
     * @return the commit id
     */
    public String getCommitId() {
        return commitId;
    }

    /**
     * Set new commit id
     *
     * @param commitId the new commit id
     */
    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    /**
     * Get Version control system type
     *
     * @return the vcsType
     */
    public String getVcsType() {
        return vcsType;
    }

    /**
     * Set version control system
     *
     * @param vcsType the new vcs type
     */
    public void setVcsType(String vcsType) {
        this.vcsType = vcsType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

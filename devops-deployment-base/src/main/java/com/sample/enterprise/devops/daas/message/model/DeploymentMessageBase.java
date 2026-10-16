package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.message.aware.JenkinsInfoAware;

import jakarta.validation.Valid;

public class DeploymentMessageBase implements JenkinsInfoAware {

    @Valid
    private MessageDispatchHeader header = new MessageDispatchHeader();

    @Valid
    private MessageJenkinsInfo jenkinsInfo = new MessageJenkinsInfo();

    /*
     * (non-Javadoc)
     *
     * @see com.sample.devops.deployment.message.aware.HeaderInfoAware#getHeader()
     */
    @Override
    public MessageDispatchHeader getHeader() {
        return header;
    }

    @Override
    public void setHeader(MessageDispatchHeader header) {
        this.header = header;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sample.devops.deployment.message.aware.JenkinsInfoAware#getJenkinsInfo(
     * )
     */
    @Override
    public MessageJenkinsInfo getJenkinsInfo() {
        return jenkinsInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sample.devops.deployment.message.aware.JenkinsInfoAware#setJenkinsInfo(
     * com.sample.devops.deployment.message.MessageJenkinsInfo)
     */
    @Override
    public void setJenkinsInfo(MessageJenkinsInfo jenkinsInfo) {
        this.jenkinsInfo = jenkinsInfo;
    }

    @Override
    public String toString() {
        return "DeploymentMessageBase{" + "header=" + header + ", jenkinsInfo=" + jenkinsInfo + '}';
    }
}

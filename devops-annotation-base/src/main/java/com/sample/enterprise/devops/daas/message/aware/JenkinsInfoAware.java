package com.sample.enterprise.devops.daas.message.aware;

import com.sample.enterprise.devops.daas.message.model.MessageJenkinsInfo;

public interface JenkinsInfoAware extends HeaderInfoAware {

    /**
     * Gets the jenkins info.
     *
     * @return the jenkins info
     */
    MessageJenkinsInfo getJenkinsInfo();

    /**
     * Sets the jenkins info.
     *
     * @param jenkinsInfo the new jenkins info
     */
    void setJenkinsInfo(MessageJenkinsInfo jenkinsInfo);

}

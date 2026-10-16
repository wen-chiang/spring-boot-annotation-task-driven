package com.sample.enterprise.devops.daas.message.model;

import java.util.Map;

public class GenericSecretInfo {
    private Map<String, String> secretContent;

    /**
     * Instantiates a new Generic secret info.
     */
    public GenericSecretInfo() {
    }

    /**
     * Instantiates a new Generic secret info.
     *
     * @param secretContent the secret content
     */
    public GenericSecretInfo(Map<String, String> secretContent) {
        this.secretContent = secretContent;
    }

    /**
     * Gets secret content.
     *
     * @return the secret content
     */
    public Map<String, String> getSecretContent() {
        return secretContent;
    }

    /**
     * Sets secret content.
     *
     * @param secretContent the secret content
     */
    public void setSecretContent(Map<String, String> secretContent) {
        this.secretContent = secretContent;
    }

    @Override
    public String toString() {
        return "GenericSecretInfo{" + "secretContent=" + secretContent + '}';
    }
}

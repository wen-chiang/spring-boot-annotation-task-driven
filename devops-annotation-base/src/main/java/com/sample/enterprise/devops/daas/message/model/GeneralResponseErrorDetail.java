package com.sample.enterprise.devops.daas.message.model;

import java.util.ArrayList;
import java.util.List;

public class GeneralResponseErrorDetail {

    private String errorCode;
    private String errorMessage;
    private List<String> adminResponse = new ArrayList<>();
    private List<String> userResponse = new ArrayList<>();

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the admin response.
     *
     * @return the admin response
     */
    public List<String> getAdminResponse() {
        return adminResponse;
    }

    /**
     * Sets the admin response.
     *
     * @param adminResponse the new admin response
     */
    public void setAdminResponse(List<String> adminResponse) {
        this.adminResponse = adminResponse;
    }

    /**
     * Gets the user response.
     *
     * @return the user response
     */
    public List<String> getUserResponse() {
        return userResponse;
    }

    /**
     * Sets the user response.
     *
     * @param userResponse the new user response
     */
    public void setUserResponse(List<String> userResponse) {
        this.userResponse = userResponse;
    }

    @Override
    public String toString() {
        return "GeneralResponseErrorDetail{" + "errorCode='" + errorCode + '\'' + ", errorMessage='" + errorMessage + '\'' + ", adminResponse=" + adminResponse + ", userResponse=" + userResponse + '}';
    }
}

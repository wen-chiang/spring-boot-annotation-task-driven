package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.constants.DaasMsgPropertyKeysConstants;

public class TLSSecretInfo {

    private String referenceNumber;
    private String authCode;
    private String keyStoreFileName;
    private String keyStorePassword;

    /**
     * Instantiates a new Tls secret info.
     */
    public TLSSecretInfo() {
    }

    /**
     * Instantiates a new Tls secret info.
     *
     * @param refereanceNumber the refereance number
     * @param authCode         the auth code
     * @param keyStoreFileName the key store file name
     * @param keyStorePassword the key store password
     */
    public TLSSecretInfo(String refereanceNumber, String authCode, String keyStoreFileName, String keyStorePassword) {
        this.referenceNumber = refereanceNumber;
        this.authCode = authCode;
        this.keyStoreFileName = keyStoreFileName;
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * Instantiates a new Tls secret info.
     *
     * @param refereanceNumber the refereance number
     * @param authCode         the auth code
     */
    public TLSSecretInfo(String refereanceNumber, String authCode) {
        this(refereanceNumber, authCode, DaasMsgPropertyKeysConstants.DEFAULT_KEY_STORE_FILE_NAME, DaasMsgPropertyKeysConstants.DEFAULT_KEY_STORE_PASSWORD);
    }

    /**
     * Gets reference number.
     *
     * @return the reference number
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets reference number.
     *
     * @param referenceNumber the reference number
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    /**
     * Gets auth code.
     *
     * @return the auth code
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets auth code.
     *
     * @param authCode the auth code
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Gets key store file name.
     *
     * @return the key store file name
     */
    public String getKeyStoreFileName() {
        return keyStoreFileName;
    }

    /**
     * Sets key store file name.
     *
     * @param keyStoreFileName the key store file name
     */
    public void setKeyStoreFileName(String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
    }

    /**
     * Gets key store password.
     *
     * @return the key store password
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * Sets key store password.
     *
     * @param keyStorePassword the key store password
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    @Override
    public String toString() {
        return "TLSSecretInfo{" + "referenceNumber='" + referenceNumber + '\'' + ", authCode='" + authCode + '\'' + ", keyStoreFileName='" + keyStoreFileName + '\'' + ", keyStorePassword='" + keyStorePassword + '\'' + '}';
    }
}

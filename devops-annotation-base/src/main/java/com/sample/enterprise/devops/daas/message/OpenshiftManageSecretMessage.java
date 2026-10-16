package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.message.model.GenericSecretInfo;
import com.sample.enterprise.devops.daas.message.model.TLSSecretInfo;
import com.sample.enterprise.devops.daas.message.model.validate.SecretMessageRequired;
import org.springframework.core.io.ByteArrayResource;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class OpenshiftManageSecretMessage extends OpenshiftDeploymentMessage {

    @NotEmpty(groups = {SecretMessageRequired.class})
    private String namespace;

    @NotEmpty(groups = {SecretMessageRequired.class})
    private String secretName;

    @NotEmpty(groups = {SecretMessageRequired.class})
    private String userName;


    @NotEmpty(groups = {SecretMessageRequired.class})
    @Pattern(regexp = "tls|generic|keystore", groups = {SecretMessageRequired.class})
    private String manageSecretRequestType;

    private ByteArrayResource fileBytes;

    private TLSSecretInfo tlsSecretInfo;
    private GenericSecretInfo genericSecretInfo;

    /**
     * Instantiates a new Openshift manage secret message.
     */
    public OpenshiftManageSecretMessage() {
    }

    /**
     * Instantiates a new Openshift manage secret message.
     *
     * @param projectName             the project name
     * @param namespace               the namespace
     * @param secretName              the secret name
     * @param userName                the user name
     * @param manageSecretRequestType the manage secret request type
     */
    public OpenshiftManageSecretMessage(String projectName, String namespace, String secretName, String userName, String manageSecretRequestType) {
        super();
        this.getJenkinsInfo().setProjectName(projectName);
        this.namespace = namespace;
        this.secretName = secretName;
        this.userName = userName;
        this.manageSecretRequestType = manageSecretRequestType;
    }

    /**
     * Gets namespace.
     *
     * @return the namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Sets namespace.
     *
     * @param namespace the namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Gets secret name.
     *
     * @return the secret name
     */
    public String getSecretName() {
        return secretName;
    }

    /**
     * Sets secret name.
     *
     * @param secretName the secret name
     */
    public void setSecretName(String secretName) {
        this.secretName = secretName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets manage secret request type.
     *
     * @return the manage secret request type
     */
    public String getManageSecretRequestType() {
        return manageSecretRequestType;
    }

    /**
     * Sets manage secret request type.
     *
     * @param manageSecretRequestType the manage secret request type
     */
    public void setManageSecretRequestType(String manageSecretRequestType) {
        this.manageSecretRequestType = manageSecretRequestType;
    }

    /**
     * Gets tls secret info.
     *
     * @return the tls secret info
     */
    public TLSSecretInfo getTlsSecretInfo() {
        return tlsSecretInfo;
    }

    /**
     * Sets tls secret info.
     *
     * @param tlsSecretInfo the tls secret info
     */
    public void setTlsSecretInfo(TLSSecretInfo tlsSecretInfo) {
        this.tlsSecretInfo = tlsSecretInfo;
    }

    /**
     * Gets generic secret info.
     *
     * @return the generic secret info
     */
    public GenericSecretInfo getGenericSecretInfo() {
        return genericSecretInfo;
    }

    /**
     * Sets generic secret info.
     *
     * @param genericSecretInfo the generic secret info
     */
    public void setGenericSecretInfo(GenericSecretInfo genericSecretInfo) {
        this.genericSecretInfo = genericSecretInfo;
    }

    public ByteArrayResource getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(ByteArrayResource fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return "OpenshiftManageSecretMessage{" +
                "namespace='" + namespace + '\'' +
                ", secretName='" + secretName + '\'' +
                ", userName='" + userName + '\'' +
                ", manageSecretRequestType='" + manageSecretRequestType + '\'' +
                ", fileBytes=" + fileBytes +
                ", tlsSecretInfo=" + tlsSecretInfo +
                ", genericSecretInfo=" + genericSecretInfo +
                '}';
    }
}

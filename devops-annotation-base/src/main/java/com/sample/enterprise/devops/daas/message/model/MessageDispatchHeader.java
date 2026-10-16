package com.sample.enterprise.devops.daas.message.model;

import com.sample.enterprise.devops.daas.message.model.validate.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.sample.enterprise.devops.daas.constants.MessageConstants.API_VERSION_V2;

public class MessageDispatchHeader {

    @NotEmpty(groups = {HeaderRequired.class})
    @Pattern.List({
            @Pattern(regexp = "openshift", groups = {OpenshiftRequired.class}),
            @Pattern(regexp = "chef", groups = {ChefRequired.class}),
            @Pattern(regexp = "ansible", groups = {AnsibleRequired.class}),
            @Pattern(regexp = "enterprise", groups = {EnterpriseRequired.class})
    })
    private String deployType = null;

    @NotEmpty(groups = {HeaderRequired.class})
    @Pattern.List({
            @Pattern(regexp = "^$|v2", groups = {OpenshiftRequired.class}),
            @Pattern(regexp = "^$|v1", groups = {AnsibleRequired.class}),
            @Pattern(regexp = "^$|v1", groups = {ChefRequired.class})
    })
    private String apiVersion = API_VERSION_V2;

    @NotEmpty(groups = {HeaderRequired.class})
    @Pattern.List({
            @Pattern(regexp = "ci|dev|trial|test|uat|qa|prod|prod_.*", groups = {OpenshiftRequired.class}),
            @Pattern(regexp = "ci|dev|trial|test|uat|qa|prod", groups = {AnsibleRequired.class}),
            @Pattern(regexp = "ci|dev|trial|test|uat|qa|prod", groups = {ChefRequired.class})
    })
    private String promoteEnv = null;

    private String promoteEnvSubIndex = "";


    @NotEmpty(groups = {HeaderRequired.class})
    @Size(min = 1, max = 100, groups = {HeaderRequired.class})
    private String correlationId = null;

    /**
     * Gets the deploy type.
     *
     * @return the deploy type
     */
    public String getDeployType() {
        return deployType;
    }

    /**
     * Sets the deploy type.
     *
     * @param deploymentType the new deploy type
     */
    public void setDeployType(String deploymentType) {
        deployType = deploymentType;
    }

    /**
     * Gets the deploy version.
     *
     * @return the deploy version
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Sets the deploy version.
     *
     * @param deployVersion the new deploy version
     */
    public void setApiVersion(String deployVersion) {
        this.apiVersion = deployVersion;
    }

    /**
     * Gets the promote env.
     *
     * @return the promote env
     */
    public String getPromoteEnv() {
        return promoteEnv;
    }

    /**
     * Sets the promote env.
     *
     * @param env the new promote env
     */
    public void setPromoteEnv(String env) {
        promoteEnv = env;
    }

    /**
     * Gets the correlation id.
     *
     * @return the correlation id
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the correlation id.
     *
     * @param correlationId the new correlation id
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getPromoteEnvSubIndex() {
        return promoteEnvSubIndex;
    }

    public void setPromoteEnvSubIndex(String promoteEnvSubIndex) {
        this.promoteEnvSubIndex = promoteEnvSubIndex;
    }

    @Override
    public String toString() {
        return "MessageDispatchHeader{" + "deployType='" + deployType + '\'' + ", apiVersion='" + apiVersion + '\'' + ", promoteEnv='" + promoteEnv + '\'' + ", correlationId='" + correlationId + '\'' + '}';
    }
}

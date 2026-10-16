package com.sample.enterprise.devops.daas.message.session;

import com.sample.enterprise.devops.daas.message.model.SessionResponseErrorDetail;
import com.sample.enterprise.devops.daas.message.model.SessionResponseProcessDetail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProcedureSessionBase {

    private SessionResponseProcessDetail processDetail = new SessionResponseProcessDetail();
    private SessionResponseErrorDetail errorDetail = new SessionResponseErrorDetail();
    private Properties properties = new Properties();
    private Map<String, Object> inputData = new HashMap<>();


    /**
     * Gets the properties.
     *
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the properties.
     *
     * @param properties the new properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Gets the process detail.
     *
     * @return the process detail
     */
    public SessionResponseProcessDetail getProcessDetail() {
        return processDetail;
    }

    /**
     * Sets the process detail.
     *
     * @param processDetail the new process detail
     */
    public void setProcessDetail(SessionResponseProcessDetail processDetail) {
        this.processDetail = processDetail;
    }

    /**
     * Gets the error detail.
     *
     * @return the error detail
     */
    public SessionResponseErrorDetail getErrorDetail() {
        return errorDetail;
    }

    /**
     * Sets the error detail.
     *
     * @param errorDetail the new error detail
     */
    public void setErrorDetail(SessionResponseErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    /**
     * Gets input data.
     *
     * @return the input data
     */
    public Map<String, Object> getInputData() {
        return inputData;
    }

    /**
     * Sets input data.
     *
     * @param inputData the input data
     */
    public void setInputData(Map<String, Object> inputData) {
        this.inputData = inputData;
    }


}

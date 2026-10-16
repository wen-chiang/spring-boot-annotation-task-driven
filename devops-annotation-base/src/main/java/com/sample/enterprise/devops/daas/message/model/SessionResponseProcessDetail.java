package com.sample.enterprise.devops.daas.message.model;

import java.util.ArrayList;
import java.util.List;

public class SessionResponseProcessDetail {

    private List<String> processLog = new ArrayList<>();

    /**
     * Gets the process log.
     *
     * @return the process log
     */
    public List<String> getProcessLog() {
        return processLog;
    }

    /**
     * Sets the process log.
     *
     * @param processLog the new process log
     */
    public void setProcessLog(List<String> processLog) {
        this.processLog = processLog;
    }

}

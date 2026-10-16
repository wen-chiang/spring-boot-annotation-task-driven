package com.sample.enterprise.devops.openshift.utils;

public class LogLevelPropertiesUtil {
    private static final LogLevelPropertiesUtil INSTANCE = new LogLevelPropertiesUtil();

    private LogLevelPropertiesUtil() {
    }

    public static LogLevelPropertiesUtil getInstance() {
        return INSTANCE;
    }

    public void setLogLevel(String level) {
        // no-op placeholder for the missing enterprise utility
    }
}


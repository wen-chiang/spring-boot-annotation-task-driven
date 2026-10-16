package com.sample.enterprise.devops.daas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface to satisfy all logging needs. Any class that would like to get logger just simply implement this interface
 */
public interface Loggable {

    Logger logger = loggerS();

    /**
     * Logger logger.
     *
     * @return the logger
     */
    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass().getName());
    }

    /**
     * Logger s logger.
     *
     * @return the logger
     */
    static Logger loggerS() {
        return LoggerFactory.getLogger(new Object() {
        }.getClass().getEnclosingClass().getName());
    }

}
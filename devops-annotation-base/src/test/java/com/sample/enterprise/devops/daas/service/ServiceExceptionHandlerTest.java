package com.sample.enterprise.devops.daas.service;

import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ServiceExceptionHandlerTest {

    @Test
    public void verifyServiceExceptionHandlerSuccess() {
        try {
            ServiceExceptionHandler handler = new ServiceExceptionHandler();
            handler.updateCauseByException(new GeneralResponseMessage(), new Exception("Test"));
        } catch (Exception exp) {
            fail();
        }
    }
}

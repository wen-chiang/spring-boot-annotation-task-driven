package com.sample.enterprise.devops.daas.service;

import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.util.ExceptionReviewerUtil;

public class ServiceExceptionHandler {

    /**
     * Update cause by exception.
     *
     * @param response the response
     * @param exp      the exp
     */
    public void updateCauseByException(GeneralResponseMessage response, Exception exp) {
        ExceptionReviewerUtil reviewer = new ExceptionReviewerUtil();
        response.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
        response.getErrorDetail().setErrorMessage(exp.getMessage());
        response.getErrorDetail().setErrorCode("INTERNAL_SERVICE_EXCEPTION");
        response.getErrorDetail().getAdminResponse().addAll(reviewer.getProjectCauseExceptionTrace(exp, 3));
        exp.printStackTrace();
    }
}

package com.sample.enterprise.devops.daas.proc;

import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.util.GeneralErrorUtil;

public abstract class DaasRoutineBase implements DaasRoutine {

    protected void updateErrorResponse(DaasDataHolder bean, String index, GeneralErrorUtil util) {
        GeneralResponseMessage response = bean.getResponseMsg();
        String errorKey = this.getClass().getSimpleName() + "-" + index;

        response.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
        response.getErrorDetail().setErrorCode(errorKey);
        util.storeErrorDetail(response.getErrorDetail(), errorKey);

    }
}

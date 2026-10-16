package com.sample.enterprise.devops.daas.util;

import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class ValidateObjUtil {

    public <T> void validateObj(T theObject, GeneralResponseMessage responseMsg, Class<?>... groups) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(theObject, groups);
        if (constraintViolations.size() > 0) {
            responseMsg.setStatus(DeploymentConstants.FAIL_STATUS_KEY);
            for (ConstraintViolation<T> cv : constraintViolations) {
                responseMsg.getErrorDetail().getUserResponse().add(cv.getPropertyPath().toString() + ":" + cv.getInvalidValue() + " - " + cv.getMessage());
            }
        }
    }
}

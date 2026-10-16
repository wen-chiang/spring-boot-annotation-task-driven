package com.sample.enterprise.devops.daas.message.validator;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class OpenshiftZoneApiVersionValidator implements ConstraintValidator<OpenshiftZoneApiVersionMatch, Object> {

    private String zoneFieldName;
    private String apiVersionFieldName;
    private String promoteEnvFieldName;

    @Override
    public void initialize(final OpenshiftZoneApiVersionMatch annotation) {
        zoneFieldName = annotation.zoneFieldName();
        apiVersionFieldName = annotation.apiVersionFieldName();
        promoteEnvFieldName = annotation.promoteEnvFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        try {
            final String zoneFieldValue = BeanUtils.getProperty(value, zoneFieldName);
            final String promoteEnvFieldValue = BeanUtils.getProperty(value, promoteEnvFieldName);
            final String apiVersionFieldValue = BeanUtils.getProperty(value, apiVersionFieldName);

            Class ZoneInfoFinder = Class.forName("com.sample.enterprise.devops.daas.proc.openshift.constants.ZoneInfoFinder");
            Method toZoneInfo = ZoneInfoFinder.getDeclaredMethod("toZoneInfo", String.class, String.class, String.class, String.class);
            Object result = toZoneInfo.invoke(null, zoneFieldValue, promoteEnvFieldValue, apiVersionFieldValue, null);
            if (result == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("no Openshift platform match with promoteEnv:" + promoteEnvFieldValue + " and apiVersion:" + apiVersionFieldValue).addPropertyNode(zoneFieldName).addConstraintViolation();
                return false;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return true;

    }
}

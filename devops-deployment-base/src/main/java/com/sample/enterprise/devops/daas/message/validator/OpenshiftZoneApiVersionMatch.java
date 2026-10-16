package com.sample.enterprise.devops.daas.message.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = OpenshiftZoneApiVersionValidator.class)
@Documented
public @interface OpenshiftZoneApiVersionMatch {
    String zoneFieldName() default "jenkinsInfo.zone";

    String apiVersionFieldName() default "header.apiVersion";

    String promoteEnvFieldName() default "header.promoteEnv";

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
package com.sample.enterprise.devops.daas.annotation.deploy;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OpenshiftAcceptVersion {
    String[] versions() default {};
}

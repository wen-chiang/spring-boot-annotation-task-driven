package com.sample.enterprise.devops.daas.annotation.deploy;

import com.sample.enterprise.devops.daas.constants.enums.ProcPropertyType;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Inherited
@Documented
public @interface CommonAccessible {
    ProcPropertyType value() default ProcPropertyType.BEAN;
}

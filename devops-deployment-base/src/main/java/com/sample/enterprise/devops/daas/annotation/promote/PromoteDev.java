package com.sample.enterprise.devops.daas.annotation.promote;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Inherited
@Documented
@PromoteBase(name = "dev")
public @interface PromoteDev {

}


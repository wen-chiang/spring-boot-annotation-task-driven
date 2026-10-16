package com.sample.enterprise.devops.daas.controller;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody

public @interface DaasEndpoint {
    @AliasFor(annotation = RequestMapping.class, attribute = "value") String endpoint();

    @AliasFor(annotation = RequestMapping.class, attribute = "method") RequestMethod[] method() default {RequestMethod.POST};

    @AliasFor(annotation = RequestMapping.class, attribute = "consumes") String consumes() default MediaType.APPLICATION_JSON_VALUE;

    @AliasFor(annotation = RequestMapping.class, attribute = "produces") String produces() default MediaType.APPLICATION_JSON_VALUE;
}

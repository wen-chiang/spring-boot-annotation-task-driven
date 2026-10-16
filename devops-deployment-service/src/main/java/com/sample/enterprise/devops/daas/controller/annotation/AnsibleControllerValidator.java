package com.sample.enterprise.devops.daas.controller.annotation;

import com.sample.enterprise.devops.daas.message.model.validate.AnsibleRequired;
import com.sample.enterprise.devops.daas.message.model.validate.HeaderRequired;
import com.sample.enterprise.devops.daas.message.model.validate.JenkinsInfoRequired;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated({JenkinsInfoRequired.class, HeaderRequired.class, AnsibleRequired.class})
public @interface AnsibleControllerValidator {
}

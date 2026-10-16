package com.sample.enterprise.devops.daas.annotation.endpoint;

import com.sample.enterprise.devops.daas.annotation.routine.EvenFailed;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Inherited
@Documented
@EvenFailed
public @interface ValidateChangeRecord {
}

package com.sample.enterprise.devops.daas.sampleApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Minimal Spring Boot bootstrap for the annotation-driven proof of concept.
 *
 * <p>This test application intentionally keeps the wiring very small: it exists only to
 * register the sample service and the annotated routines, so the project can demonstrate how
 * {@link com.sample.enterprise.devops.daas.service.DaasServiceCore} discovers and executes
 * routines through metadata rather than hard-coded imperative flow.</p>
 */
@SpringBootApplication
@WebAppConfiguration
@EnableAutoConfiguration
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}

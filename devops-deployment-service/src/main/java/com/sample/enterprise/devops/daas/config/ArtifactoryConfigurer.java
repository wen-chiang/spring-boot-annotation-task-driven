package com.sample.enterprise.devops.daas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArtifactoryConfigurer {

    @Value("${artifactory.host}")
    String artifactoryHost;

    @Value("${artifactory.port}")
    String artifactoryPort;

    @Value("${artifactory.protocol}")
    String artifactoryHttpProtocol;

}

package com.sample.enterprise.devops.daas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Devops Deployment as Service REST API")
                        .description("REST API to support deployment backend endpoint")
                        .version("1.0"));
    }
}
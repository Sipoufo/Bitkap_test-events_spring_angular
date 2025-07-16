package com.bitkap.event_manager_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SIPOUFO Yvan
 *
 * Configuration for documentation.
 */

@Configuration
public class OpenAPIConfig {

    @Value("${open-api.version}")
    private String openApiVersion;

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event manager API")
                        .description("This is Rest API for event manager api")
                        .version(openApiVersion)
                );
    }
}

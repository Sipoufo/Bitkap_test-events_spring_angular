package com.bitkap.event_manager_api.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author SIPOUFO Yvan
 * <p>
 * Configuration for documentation.
 */

@Configuration
public class OpenAPIConfig {

    @Value("${open-api.version}")
    private String openApiVersion;
    private static final String OAUTH_SCHEME = "auth";

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .contact(
                                        new Contact()
                                                .name("BitKap-test")
                                                .url("http://bitkap.com/test")
                                )
                                .title("Event manager API")
                                .description("This is Rest API for event manager api")
                                .version(openApiVersion)
                )
                .servers(Arrays.asList(
                        new Server()
                                .description("Dev")
                                .url("http://localhost:8080"),
                        new Server()
                                .description("Production")
                                .url("http://server-pro.com")
                ))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(OAUTH_SCHEME)
                )
                .components(new Components()
                        .addSecuritySchemes(OAUTH_SCHEME, securityScheme()));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .description("JWT auth description")
                .scheme("bearer")
                .type(SecurityScheme.Type.OAUTH2)
                .flows(
                        new OAuthFlows()
                                .authorizationCode(
                                        new OAuthFlow()
                                                .authorizationUrl("https://sso.bitkap.africa/realms/bitkap_dev/protocol/openid-connect/auth")
                                                .refreshUrl("https://sso.bitkap.africa/realms/bitkap_dev/protocol/openid-connect/token")
                                                .tokenUrl("https://sso.bitkap.africa/realms/bitkap_dev/protocol/openid-connect/token")
                                                .scopes(new Scopes())
                                )
                )
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);
    }
}

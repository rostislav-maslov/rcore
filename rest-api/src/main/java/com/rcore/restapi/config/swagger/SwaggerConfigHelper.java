package com.rcore.restapi.config.swagger;

import io.swagger.models.auth.In;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwaggerConfigHelper {

    public static List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(securityReferences())
                .build());
    }

    public static List<SecurityReference> securityReferences() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("x-auth-token", authorizationScopes),
                new SecurityReference("x-device-token", authorizationScopes),
                new SecurityReference("x-device-type", authorizationScopes)
        );
    }

    public static List<SecurityScheme> securitySchemes() {
        return Arrays.asList(
                new ApiKey("x-auth-token", "X-Auth-Token", In.HEADER.name()),
                new ApiKey("x-device-token", "X-Device-Token", In.HEADER.name()),
                new ApiKey("x-device-type", "X-Device-Type", In.HEADER.name())
        );
    }

}

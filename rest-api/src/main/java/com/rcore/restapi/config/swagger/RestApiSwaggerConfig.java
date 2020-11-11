package com.rcore.restapi.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Profile("dev")
public class RestApiSwaggerConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Базовое API из Rcore.rest-api")
                        .build())
                .groupName("rest-api")
                .securityContexts(SwaggerConfigHelper.securityContexts())
                .securitySchemes(SwaggerConfigHelper.securitySchemes())
                .select().apis(RequestHandlerSelectors.basePackage("com.rcore"))
                .paths(PathSelectors.any())
                .build();
    }


}

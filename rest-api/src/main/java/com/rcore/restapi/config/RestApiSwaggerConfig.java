package com.rcore.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class RestApiSwaggerConfig {

    @Bean
    public Docket adminCoreApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Базовое API из Rcore.rest-api")
                        .build())
                .groupName("rest-api")
                .select().apis(RequestHandlerSelectors.basePackage("com.rcore"))
                .paths(PathSelectors.any())
                .build();
    }
}

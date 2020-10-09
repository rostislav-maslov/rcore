package com.rcore.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@EnableSwagger2
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
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("X-Auth-Token")
                                        .description("Токен авторизации")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build(),
                                new ParameterBuilder()
                                        .name("X-Device-Token")
                                        .description("Токен устройства")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build(),
                                new ParameterBuilder()
                                        .name("X-Device-Type")
                                        .description("Тип устройства")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build())
                )
                .select().apis(RequestHandlerSelectors.basePackage("com.rcore"))
                .paths(PathSelectors.any())
                .build();
    }
}

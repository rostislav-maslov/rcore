package com.rcore.admincore.config;

import com.rcore.restapi.config.swagger.SwaggerConfigHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AdminCoreSwaggerConfig {

    @Bean
    public Docket adminCoreApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Базовое API")
                        .build())
                .groupName("admin-core")
                .securityContexts(SwaggerConfigHelper.securityContexts())
                .securitySchemes(SwaggerConfigHelper.securitySchemes())
                .select().apis(RequestHandlerSelectors.basePackage("com.rcore"))
                .paths(PathSelectors.any())
                .build();
    }
}
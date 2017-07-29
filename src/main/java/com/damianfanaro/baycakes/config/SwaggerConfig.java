package com.damianfanaro.baycakes.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO: Complete with description
 *
 * @author dfanaro
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${baycakes.token.header}")
    private String tokenHeader;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(apiInfo());
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                "",
                ApiKeyVehicle.HEADER,
                "Authorization",
                "," /*scope separator*/);
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("JWT-Token", tokenHeader, "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bay Cakes - API Documentation")
                .description("Available REST Endpoints")
                .version("2.0")
                .build();
    }

}
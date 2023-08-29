package com.eztech.springbase.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Knife4j 接口文档配置
 *
 * @author chenqinru
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class Knife4jConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("api")
                .apiInfo(apiInfo())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.eztech.springbase.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Knife4j Restful Apis")
                .description("Knife4j Restful Apis")
                .termsOfServiceUrl("http://localhost:8999/")
                .contact(new Contact("chenqinru", "", "chenqinru0@gmail.com"))
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", In.HEADER.toValue());

        return Collections.singletonList(apiKey);
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(Collections.singletonList(
                        new SecurityReference("Authorization",
                                new AuthorizationScope[]{
                                        new AuthorizationScope("global", "")})))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
    }
}
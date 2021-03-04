package com.example.coins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${api.groupName}")
    private String groupName;

    @Value("${api.title}")
    private String title;

    @Value("${contact.name}")
    private String name;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(info())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(apiPaths())
                .build();
    }

    private ApiInfo info() {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(name, "", ""))
                .build();
    }

    private Predicate<String> apiPaths() {
        return PathSelectors.regex(".*/invoice.*")
                .or(PathSelectors.regex(".*/payment.*"))
                .or(PathSelectors.regex(".*/receiver.*"))
                .or(PathSelectors.regex(".*/sender.*"));
    }

}

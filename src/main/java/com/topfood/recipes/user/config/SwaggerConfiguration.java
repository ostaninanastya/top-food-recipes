package com.nc.ncproject.config;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration("LaptopsSwaggerConfiguration")
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
    public Docket laptopsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.nc.ncproject"))
                .paths(regex("/api/.*"))
                .build()
                .groupName("laptops")
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Laptops REST API")
                .description("This API allows to perform all operations related to laptops")
                .version("1.0")
                .build();
    }
}

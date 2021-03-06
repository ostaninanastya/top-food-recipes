package com.topfood.recipes.ingredientRecipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration("IngredientRecipeSwaggerConfiguration")
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket ingredientRecipesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.topfood.recipes.ingredientRecipe"))
                .paths(regex("/api/.*"))
                .build()
                .groupName("ingredientRecipe")
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Ingredients of recipes REST API")
                .description("This API allows to perform all operations related to recipes")
                .version("1.0")
                .build();
    }
}

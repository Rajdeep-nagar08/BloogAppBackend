package com.rajdeep.blogapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean

    public OpenAPI openAPI(){

        String schemeName= "bearerScheme";
        return new OpenAPI()
                .info(new Info().title("Blogging Application").description("These are the api's of Blogging Application developed by Rajdeep Nagar").version("v0.01").license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().url("xyz.com").description("Description for external documentation"))
                .components(new Components().addSecuritySchemes(schemeName,new SecurityScheme().name(schemeName).type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer")))
                .addSecurityItem(new SecurityRequirement().addList(schemeName));
    }




}

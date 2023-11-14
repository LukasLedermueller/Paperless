package at.fhtw.swkom.paperless.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
@Configuration
public class SpringDocConfiguration {

    @Bean(name = "at.fhtw.swkom.paperless.config.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Paperless Rest Server")
                                .description("No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)")
                                .version("1.0")
                )
        ;
    }
}
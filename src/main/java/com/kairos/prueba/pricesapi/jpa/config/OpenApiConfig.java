package com.kairos.prueba.pricesapi.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuration class for customizing the OpenAPI/Swagger documentation for the Prices API.
 * <p>
 * This class provides configuration for the OpenAPI documentation, setting the title, version,
 * description, and contact information for the API.
 * </p>
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures and returns a custom {@link OpenAPI} bean to define the API documentation details.
     * <p>
     * This method sets up metadata for the Prices API, including the title, version, description, and
     * contact details for the API documentation.
     * </p>
     *
     * @return a configured {@link OpenAPI} instance with custom metadata for the Prices API
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Prices API") // Title of the API
                    .version("1.0") // Version of the API
                    .contact(new Contact()
                        .name("Francisco Cilleruelo") // Contact name
                        .email("francisco.cilleruelo@gmail.com")) // Contact email
                    .description("RestFul API service to get the applicable product price")); // Description of the API
    }
}

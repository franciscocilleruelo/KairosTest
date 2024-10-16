package com.kairos.prueba.pricesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Prices API application.
 * <p>
 * This is the entry point for the Spring Boot application. The {@code @SpringBootApplication} annotation
 * enables auto-configuration, component scanning, and allows for configuration properties.
 * </p>
 */
@SpringBootApplication
public class PricesapiApplication {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        // Launch the Spring Boot application
        SpringApplication.run(PricesapiApplication.class, args);
    }
}

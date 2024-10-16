package com.kairos.prueba.pricesapi.rest.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

/**
 * DTO that represents the input data for the price query.
 * <p>
 * This DTO encapsulates the parameters required for querying the applicable price of a product,
 * including the product ID, brand ID, and the date and time of application.
 * </p>
 */
public record PriceRequestDTO(
        /**
         * Product ID, which cannot be null.
         */
        @NotNull(message = "Product ID cannot be null") Long productId,

        /**
         * Brand ID, which cannot be null.
         */
        @NotNull(message = "Brand ID cannot be null") Long brandId,

        /**
         * Application date and time for which the price is requested, which cannot be null.
         */
        @NotNull(message = "Application date cannot be null") LocalDateTime applicationDate) {
}

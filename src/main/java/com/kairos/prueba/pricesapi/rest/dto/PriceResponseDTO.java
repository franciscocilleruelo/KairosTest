package com.kairos.prueba.pricesapi.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO that represents the response for the price query.
 * <p>
 * This DTO encapsulates the result of a price query, including details such as the product ID,
 * brand ID, price list, the applicable date range for the price, the price itself, and the currency.
 * </p>
 */
public record PriceResponseDTO(
        /**
         * The product ID for which the price is applicable.
         */
        Long productId,

        /**
         * The brand ID associated with the price.
         */
        Long brandId,

        /**
         * The price list ID that applies to this price.
         */
        Long priceList,

        /**
         * The start date and time from which the price becomes effective.
         */
        LocalDateTime startDate,

        /**
         * The end date and time until which the price remains effective.
         */
        LocalDateTime endDate,

        /**
         * The final sale price for the product.
         */
        BigDecimal price,

        /**
         * The currency in which the price is specified (ISO format, e.g., EUR).
         */
        String currency) {
}

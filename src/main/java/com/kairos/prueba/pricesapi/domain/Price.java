package com.kairos.prueba.pricesapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the Price entity in the domain.
 * <p>
 * This class models the pricing information for a product within a given time range,
 * including details like price list, brand, product, and the applicable price.
 * </p>
 */
@Data
@AllArgsConstructor
public class Price {

    /**
     * Identifier of the price list.
     */
    private Long priceList;

    /**
     * Identifier of the brand (e.g., 1 = ZARA).
     */
    private Long brandId;

    /**
     * Start date and time when the price becomes effective.
     */
    private LocalDateTime startDate;

    /**
     * End date and time when the price is no longer applicable.
     */
    private LocalDateTime endDate;

    /**
     * Identifier of the product for which this price applies.
     */
    private Long productId;

    /**
     * Priority to resolve price conflicts. Higher values take precedence if multiple
     * prices apply for the same time range.
     */
    private Integer priority;

    /**
     * The final sale price for the product.
     */
    private BigDecimal price;

    /**
     * Currency of the price in ISO format (e.g., EUR).
     */
    private String currency;
}

package com.kairos.prueba.pricesapi.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.kairos.prueba.pricesapi.domain.Price;

/**
 * Port that defines the logic for interacting with price data.
 * <p>
 * This interface represents the domain repository that abstracts the access to price data,
 * allowing the core domain logic to remain decoupled from the specific persistence mechanism.
 * Implementations of this interface will handle the retrieval of price information.
 * </p>
 */
public interface PriceRepository {

    /**
     * Finds the applicable price for a product by checking if the application date falls within
     * the valid date range for the price and considering the product and brand identifiers.
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date and time for which the price is applicable
     * @return an {@link Optional} containing the applicable {@link Price}, or empty if no price is found
     */
    Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

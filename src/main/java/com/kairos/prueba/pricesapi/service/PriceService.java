package com.kairos.prueba.pricesapi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.kairos.prueba.pricesapi.domain.Price;

/**
 * Service interface that defines the business logic for price retrieval.
 * <p>
 * This interface abstracts the operations related to finding the applicable price for
 * a product, brand, and application date. It is intended to be implemented by classes
 * that contain the core business logic for price calculations.
 * </p>
 */
public interface PriceService {

    /**
     * Finds the applicable price for a given product, brand, and application date.
     * <p>
     * This method returns the price that applies to the product based on the specified
     * brand and the date and time the price is requested for. If no price is found,
     * an empty {@link Optional} is returned.
     * </p>
     *
     * @param productId       ID of the product
     * @param brandId         ID of the brand (chain)
     * @param applicationDate Date and time for which the price is requested
     * @return an {@link Optional} containing the applicable {@link Price}, or empty if no price is found
     */
    Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);

}

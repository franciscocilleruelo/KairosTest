package com.kairos.prueba.pricesapi.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.repository.PriceRepository;
import com.kairos.prueba.pricesapi.service.PriceService;

/**
 * Service implementation that contains the business logic for retrieving prices.
 * <p>
 * This class implements the {@link PriceService} interface and uses the {@link PriceRepository}
 * to interact with the data layer and find the applicable price for a given product, brand,
 * and application date.
 * </p>
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    /**
     * Constructor to inject the {@link PriceRepository} dependency.
     *
     * @param priceRepository the repository used to retrieve price data
     */
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Finds the applicable price for a given product, brand, and application date.
     * <p>
     * This method delegates to the {@link PriceRepository} to find the price and return it.
     * </p>
     *
     * @param productId       ID of the product
     * @param brandId         ID of the brand (chain)
     * @param applicationDate Date and time for which the price is requested
     * @return an {@link Optional} containing the applicable {@link Price}, or empty if no price is found
     */
    @Override
    public Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        // Delegate the call to the repository to find the applicable price
        return priceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }
}

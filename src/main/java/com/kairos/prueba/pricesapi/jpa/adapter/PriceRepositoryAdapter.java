package com.kairos.prueba.pricesapi.jpa.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.PriceJpaRepository;
import com.kairos.prueba.pricesapi.mapper.PriceMapper;
import com.kairos.prueba.pricesapi.repository.PriceRepository;

/**
 * Adapter that implements the {@link PriceRepository} port using JPA 
 * and performs the necessary conversions from JPA entities to domain objects.
 */
@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    /**
     * Finds the applicable price for a product, brand, and application date.
     * <p>
     * If more than one price is found, the one with the highest priority is selected.
     * If only one price is found, that price is returned regardless of its priority.
     * </p>
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date and time for which the price is applicable
     * @return an {@link Optional} containing a {@link List} of applicable {@link Price} objects,
     *         or {@link Optional#empty()} if no prices are found.
     */
    @Override
    public Optional<List<Price>> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        // Fetch applicable prices using the repository, ordered by priority DESC
    	 List<Price> prices =  priceJpaRepository.findApplicablePrices(productId, brandId, applicationDate).stream()
            .map(PriceMapper.INSTANCE::toDomain) // Convert each PriceEntity to a Price domain object
            .collect(Collectors.toList());
    	 
    	// Return the list wrapped in Optional, or Optional.empty() if the list is empty
         return prices.isEmpty() ? Optional.empty() : Optional.of(prices);
    }
}

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
     * @return an {@link Optional} containing the applicable {@link Price}, or empty if no price is found
     */
    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        // Fetch applicable prices using the repository, ordered by priority DESC
        List<Price> applicablePrices = priceJpaRepository.findApplicablePrices(productId, brandId, applicationDate).stream()
            .map(PriceMapper.INSTANCE::toDomain) // Convert each PriceEntity to a Price domain object
            .collect(Collectors.toList());

        // Return the highest priority price (first element if there are multiple results)
        return applicablePrices.stream()
            .findFirst(); // Find the first price (which is the highest priority due to ordering)
    }
}

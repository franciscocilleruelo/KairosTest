package com.kairos.prueba.pricesapi.jpa.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.PriceJpaRepository;
import com.kairos.prueba.pricesapi.mapper.PriceMapper;
import com.kairos.prueba.pricesapi.repository.PriceRepository;

/**
 * Adapter that implements the {@link PriceRepository} port using JPA 
 * and performs the necessary conversions from JPA entities to domain objects.
 * <p>
 * This adapter serves as the bridge between the JPA layer and the domain layer, 
 * allowing the application to work with domain objects while the persistence 
 * details remain encapsulated within the JPA repository.
 * </p>
 */
@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    /**
     * Constructor to inject the JPA repository dependency.
     *
     * @param priceJpaRepository the JPA repository used to interact with the database
     */
    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    /**
     * Finds the applicable price for a given product, brand, and application date.
     * <p>
     * This method queries the JPA repository to find the price and then maps
     * the JPA entity to a domain object using {@link PriceMapper}.
     * </p>
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date and time for which the price is applicable
     * @return an {@link Optional} containing the applicable {@link Price}, or empty if no price is found
     */
    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        // Convert the JPA entity to a domain object using the PriceMapper
        return priceJpaRepository.findApplicablePrice(productId, brandId, applicationDate)
                .map(PriceMapper.INSTANCE::toDomain);
    }
}

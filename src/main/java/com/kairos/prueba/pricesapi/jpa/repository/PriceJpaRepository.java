package com.kairos.prueba.pricesapi.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;

/**
 * Repository interface that extends {@link JpaRepository} to manage the persistence of {@link PriceEntity}.
 * <p>
 * It includes a custom query to find the applicable price for a given product, brand, and date range.
 * </p>
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds the applicable price for a product by checking if the application date falls between 
     * the start and end dates of a price entry, with the highest priority.
     * <p>
     * The query filters the results by product ID, brand ID, and ensures the application date
     * is within the range. It also orders by priority to ensure that the highest priority price is applied.
     * </p>
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date and time for which the price is applicable
     * @return an {@link Optional} containing the applicable {@link PriceEntity}, or empty if no price is found
     */
    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
           "AND :applicationDate BETWEEN p.startDate AND p.endDate AND p.priority = 1 " +
           "ORDER BY p.priority DESC")
    Optional<PriceEntity> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

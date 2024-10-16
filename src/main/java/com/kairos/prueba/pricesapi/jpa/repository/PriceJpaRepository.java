package com.kairos.prueba.pricesapi.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;

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
     * Finds all applicable prices for a product by checking if the application date falls between 
     * the start and end dates of a price entry.
     * <p>
     * The query filters the results by product ID, brand ID, and ensures the application date
     * is within the range.
     * </p>
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the date and time for which the price is applicable
     * @return a {@link List} containing all applicable {@link PriceEntity} records
     */
    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
           "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
           "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
}

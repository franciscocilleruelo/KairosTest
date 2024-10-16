package com.kairos.prueba.pricesapi.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;

/**
 * Adaptador que implementa el puerto PriceRepository utilizando JPA.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
           "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
           "ORDER BY p.priority DESC")
    Optional<PriceEntity> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}


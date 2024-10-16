package com.kairos.prueba.pricesapi.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.kairos.prueba.pricesapi.domain.Price;

/**
 * Puerto que define la lógica para interactuar con los datos de precios.
 */
public interface PriceRepository {
    Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}


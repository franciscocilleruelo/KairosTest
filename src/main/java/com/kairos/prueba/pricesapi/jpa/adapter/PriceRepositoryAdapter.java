package com.kairos.prueba.pricesapi.jpa.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.PriceJpaRepository;
import com.kairos.prueba.pricesapi.mapper.PriceMapper;
import com.kairos.prueba.pricesapi.repository.PriceRepository;

/**
 * Adaptador que implementa el puerto PriceRepository utilizando JPA y realiza
 * las conversiones necesarias de entidades a objetos de dominio.
 */
@Component
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public Optional<Price> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceJpaRepository.findApplicablePrice(productId, brandId, applicationDate)
                .map(PriceMapper.INSTANCE::toDomain); // Convertir la entidad JPA a objeto de dominio
    }
}

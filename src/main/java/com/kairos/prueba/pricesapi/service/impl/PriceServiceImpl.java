package com.kairos.prueba.pricesapi.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.repository.PriceRepository;
import com.kairos.prueba.pricesapi.service.PriceService;

/**
 * Servicio que contiene la lógica de aplicación para la obtención de precios.
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Encuentra el precio aplicable para un producto, marca y fecha dada.
     *
     * @param productId       ID del producto.
     * @param brandId         ID de la cadena (marca).
     * @param applicationDate Fecha de aplicación.
     * @return Precio aplicable si existe.
     */
    @Override
	public Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }
}

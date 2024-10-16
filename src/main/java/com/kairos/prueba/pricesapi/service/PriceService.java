package com.kairos.prueba.pricesapi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.kairos.prueba.pricesapi.domain.Price;

public interface PriceService {

	/**
	 * Encuentra el precio aplicable para un producto, marca y fecha dada.
	 *
	 * @param productId       ID del producto.
	 * @param brandId         ID de la cadena (marca).
	 * @param applicationDate Fecha de aplicación.
	 * @return Precio aplicable si existe.
	 */
	Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);

}
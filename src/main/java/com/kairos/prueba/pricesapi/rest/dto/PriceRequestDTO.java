package com.kairos.prueba.pricesapi.rest.dto;

import java.time.LocalDateTime;

/**
 * DTO que representa los datos de entrada para la consulta de precios.
 */
public record PriceRequestDTO(Long productId, Long brandId, LocalDateTime applicationDate) {
}

package com.kairos.prueba.pricesapi.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO que representa la respuesta de la consulta de precios.
 */
public record PriceResponseDTO(Long productId, Long brandId, Long priceList, 
                               LocalDateTime startDate, LocalDateTime endDate, 
                               BigDecimal price, String currency) {
}

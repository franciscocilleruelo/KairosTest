package com.kairos.prueba.pricesapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Representa la entidad Price en el dominio.
 */
@Data
@AllArgsConstructor
public class Price {
    private Long priceList;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}


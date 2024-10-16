package com.kairos.prueba.pricesapi.jpa.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kairos.prueba.pricesapi.jpa.converter.LocalDateTimeAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad Price para la persistencia en la base de datos.
 */
@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

    @Id
    @Column(name = "price_list")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Suponiendo que la lista de precios es autogenerada
    private Long priceList;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "last_update_by", nullable = false, length = 50)
    private String lastUpdateBy;
}


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
 * Represents the Price entity for database persistence.
 * <p>
 * This entity is mapped to the "prices" table in the database and includes details about
 * the price list, product, applicable price, and the time period during which the price is valid.
 * </p>
 */
@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceEntity {

    /**
     * Identifier of the price list. This field is auto-generated.
     */
    @Id
    @Column(name = "price_list")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Assuming the price list ID is auto-generated
    private Long priceList;

    /**
     * Identifier of the brand (e.g., 1 = ZARA). Cannot be null.
     */
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    /**
     * Start date and time when the price becomes effective. Cannot be null.
     * <p>
     * This field uses {@link LocalDateTimeAttributeConverter} to convert between
     * {@code LocalDateTime} and {@code String} for database storage.
     * </p>
     */
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    /**
     * End date and time when the price is no longer applicable. Cannot be null.
     * <p>
     * This field uses {@link LocalDateTimeAttributeConverter} to convert between
     * {@code LocalDateTime} and {@code String} for database storage.
     * </p>
     */
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    /**
     * Identifier of the product for which this price applies. Cannot be null.
     */
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * Priority to resolve price conflicts. Higher values take precedence if multiple
     * prices apply for the same time range. Cannot be null.
     */
    @Column(name = "priority", nullable = false)
    private Integer priority;

    /**
     * The final sale price for the product. Cannot be null.
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * Currency of the price in ISO format (e.g., EUR). The length of the currency code is restricted to 3 characters. Cannot be null.
     */
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    /**
     * Timestamp of the last update made to the price. Cannot be null.
     */
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    /**
     * Name of the user who made the last update to the price. The length of this field is restricted to 50 characters. Cannot be null.
     */
    @Column(name = "last_update_by", nullable = false, length = 50)
    private String lastUpdateBy;
}

package com.kairos.prueba.pricesapi.jpa.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.PriceJpaRepository;
import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;

class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    private PriceEntity lowerPriorityPriceEntity;
    private PriceEntity higherPriorityPriceEntity;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a PriceEntity with lower priority
        lowerPriorityPriceEntity = new PriceEntity(
                1L, // priceList
                1L, // brandId
                LocalDateTime.of(2020, 6, 14, 0, 0, 0), // startDate
                LocalDateTime.of(2020, 6, 14, 23, 59, 59), // endDate
                35455L, // productId
                0, // priority (lower priority)
                BigDecimal.valueOf(25.45), // price
                "EUR", // currency
                LocalDateTime.of(2020, 6, 13, 14, 49, 7), // lastUpdate
                "admin" // lastUpdateBy
        );

        // Create a PriceEntity with higher priority
        higherPriorityPriceEntity = new PriceEntity(
                2L, // priceList
                1L, // brandId
                LocalDateTime.of(2020, 6, 14, 0, 0, 0), // startDate
                LocalDateTime.of(2020, 6, 14, 23, 59, 59), // endDate
                35455L, // productId
                1, // priority (higher priority)
                BigDecimal.valueOf(35.50), // price
                "EUR", // currency
                LocalDateTime.of(2020, 6, 13, 14, 49, 7), // lastUpdate
                "admin" // lastUpdateBy
        );
    }

    @Test
    void testFindApplicablePrice_Success_MultipleResults() {
        // Mock the JPA repository to return multiple results
        when(priceJpaRepository.findApplicablePrices(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(List.of(higherPriorityPriceEntity, lowerPriorityPriceEntity));

        // Call the adapter method
        Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

        // Assert that the result with the highest priority is returned
        assertTrue(result.isPresent());
        assertEquals(higherPriorityPriceEntity.getProductId(), result.get().getProductId());
        assertEquals(higherPriorityPriceEntity.getPrice(), result.get().getPrice());
    }

    @Test
    void testFindApplicablePrice_Success_SingleResult() {
        // Mock the JPA repository to return only one result
        when(priceJpaRepository.findApplicablePrices(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(List.of(lowerPriorityPriceEntity));

        // Call the adapter method
        Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

        // Assert that the single result is returned, regardless of priority
        assertTrue(result.isPresent());
        assertEquals(lowerPriorityPriceEntity.getProductId(), result.get().getProductId());
        assertEquals(lowerPriorityPriceEntity.getPrice(), result.get().getPrice());
    }

    @Test
    void testFindApplicablePrice_NotFound() {
        // Mock the JPA repository to return no results
        when(priceJpaRepository.findApplicablePrices(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(List.of());

        // Call the adapter method
        Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

        // Assert that no result is returned
        assertTrue(result.isEmpty());
    }
}

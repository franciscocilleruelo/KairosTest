package com.kairos.prueba.pricesapi.service.impl;

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
import com.kairos.prueba.pricesapi.repository.PriceRepository;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price price;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a sample Price object
        price = new Price(
                1L, // priceList
                1L, // brandId
                LocalDateTime.of(2020, 6, 14, 0, 0, 0), // startDate
                LocalDateTime.of(2020, 6, 14, 23, 59, 59), // endDate
                35455L, // productId
                1, // priority
                BigDecimal.valueOf(35.50), // price
                "EUR" // currency
        );
    }

    @Test
    void testGetApplicablePrice_Success() {
        // Mock the repository to return the sample Price
        when(priceRepository.findApplicablePrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(List.of(price)));

        // Call the service method
        Optional<Price> result = priceService.getApplicablePrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

        // Assert that the price is found and check its values
        assertTrue(result.isPresent());
        assertEquals(35455L, result.get().getProductId());
        assertEquals(1L, result.get().getBrandId());
        assertEquals(BigDecimal.valueOf(35.50), result.get().getPrice());
    }

    @Test
    void testGetApplicablePrice_NotFound() {
        // Mock the repository to return an empty Optional
        when(priceRepository.findApplicablePrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        // Call the service method
        Optional<Price> result = priceService.getApplicablePrice(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

        // Assert that no price is found
        assertTrue(result.isEmpty());
    }
}


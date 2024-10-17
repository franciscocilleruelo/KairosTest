package com.kairos.prueba.pricesapi.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.service.PriceService;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private Price price;

    @BeforeEach
    void setUp() {
    	// Initialize a sample Price object for testing
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
    void testGetPrice_Success() throws Exception {
        // Mock the service to return the sample PriceResponseDTO
        when(priceService.getApplicablePrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(price));

        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void testGetPrice_NotFound() throws Exception {
        // Mock the service to return an empty Optional
        when(priceService.getApplicablePrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Price not found"));
    }

    @Test
    void testGetPrice_InvalidDateFormat() throws Exception {
        // Attempt to send an invalid date format
        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "invalid-date"))
                .andExpect(status().isBadRequest());
    }
}

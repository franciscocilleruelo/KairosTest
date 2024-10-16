package com.kairos.prueba.pricesapi.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPriceRequest_10AM_14thJune2020() throws Exception {
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
    void testPriceRequest_16PM_14thJune2020() throws Exception {
        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(25.45));  
    }

    @Test
    void testPriceRequest_21PM_14thJune2020() throws Exception {
        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));  
    }

    @Test
    void testPriceRequest_10AM_15thJune2020() throws Exception {
        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(30.50));  
    }

    @Test
    void testPriceRequest_21PM_16thJune2020() throws Exception {
        mockMvc.perform(get("/prices")
                .param("productId", "35455")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(38.95));  
    }
}


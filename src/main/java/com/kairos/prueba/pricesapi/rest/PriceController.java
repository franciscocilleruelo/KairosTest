package com.kairos.prueba.pricesapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kairos.prueba.pricesapi.mapper.PriceMapper;
import com.kairos.prueba.pricesapi.rest.dto.PriceRequestDTO;
import com.kairos.prueba.pricesapi.rest.dto.PriceResponseDTO;
import com.kairos.prueba.pricesapi.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * REST controller that exposes the price query endpoint.
 * <p>
 * This controller handles requests to fetch the applicable price for a product,
 * based on the provided parameters such as product ID, brand ID, and application date.
 * It returns the corresponding price or a 404 error if no price is found.
 * </p>
 */
@RestController
public class PriceController {

    private final PriceService priceService;

    /**
     * Constructor to inject the {@link PriceService} dependency.
     *
     * @param priceService the service used to retrieve price information
     */
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Endpoint to get the applicable price for a product.
     * <p>
     * This method handles GET requests to fetch the price based on the input parameters 
     * provided in the {@link PriceRequestDTO}. If no applicable price is found, it throws
     * a {@link ResponseStatusException} with a 404 status.
     * </p>
     *
     * @param requestDTO DTO that contains the request parameters, including product ID, brand ID, and application date
     * @return the applicable price as a {@link PriceResponseDTO}, or a 404 error if not found
     */
    @Operation(summary = "Get applicable price for a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found"),
            @ApiResponse(responseCode = "404", description = "Price not found")
    })
    @GetMapping("/prices")
    public ResponseEntity<PriceResponseDTO> getPrice(@Valid PriceRequestDTO requestDTO) {
        // Call the service to find the applicable price, map the result to a DTO, or throw 404 if not found
        PriceResponseDTO responseDTO = priceService.getApplicablePrice(
                requestDTO.productId(),
                requestDTO.brandId(),
                requestDTO.applicationDate()
        ).map(PriceMapper.INSTANCE::toDTO)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found"));

        return ResponseEntity.ok(responseDTO);
    }
}

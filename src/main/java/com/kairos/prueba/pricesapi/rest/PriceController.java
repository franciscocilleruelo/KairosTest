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

/**
 * Controlador REST que expone el endpoint de consulta de precios.
 */
@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Endpoint para obtener el precio aplicable.
     *
     * @param requestDTO DTO que contiene los parámetros de la solicitud.
     * @return El precio aplicable o 404 si no se encuentra.
     */
    @GetMapping("/prices")
    public ResponseEntity<PriceResponseDTO> getPrice(PriceRequestDTO requestDTO) {
    	PriceResponseDTO responseDTO = priceService.getApplicablePrice(
                requestDTO.productId(),
                requestDTO.brandId(),
                requestDTO.applicationDate()
        ).map(PriceMapper.INSTANCE::toDTO)
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found"));

        return ResponseEntity.ok(responseDTO);
    }
}

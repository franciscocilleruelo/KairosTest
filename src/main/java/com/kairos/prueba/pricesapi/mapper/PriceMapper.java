package com.kairos.prueba.pricesapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;
import com.kairos.prueba.pricesapi.rest.dto.PriceResponseDTO;

/**
 * Mapper para convertir entre PriceEntity, Price (core) y PriceResponseDTO.
 */
@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price toDomain(PriceEntity entity);

    PriceEntity toEntity(Price price);

    PriceResponseDTO toDTO(Price price);
}

package com.kairos.prueba.pricesapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;
import com.kairos.prueba.pricesapi.rest.dto.PriceResponseDTO;

/**
 * Mapper interface for converting between {@link PriceEntity}, {@link Price} (domain), and {@link PriceResponseDTO}.
 * <p>
 * This mapper facilitates the conversion between different layers of the application: 
 * - The persistence layer (JPA entity).
 * - The domain layer (core business logic).
 * - The presentation layer (DTO for API responses).
 * </p>
 * <p>
 * The interface is implemented automatically by MapStruct at compile time.
 * </p>
 */
@Mapper
public interface PriceMapper {

    /**
     * An instance of the {@link PriceMapper} created by MapStruct.
     */
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    /**
     * Converts a {@link PriceEntity} (JPA entity) to a {@link Price} (domain object).
     *
     * @param entity the {@code PriceEntity} to be converted
     * @return the corresponding {@code Price} domain object
     */
    Price toDomain(PriceEntity entity);

    /**
     * Converts a {@link Price} (domain object) to a {@link PriceEntity} (JPA entity).
     *
     * @param price the {@code Price} domain object to be converted
     * @return the corresponding {@code PriceEntity} JPA entity
     */
    PriceEntity toEntity(Price price);

    /**
     * Converts a {@link Price} (domain object) to a {@link PriceResponseDTO} (DTO for API response).
     *
     * @param price the {@code Price} domain object to be converted
     * @return the corresponding {@code PriceResponseDTO} DTO for the API response
     */
    PriceResponseDTO toDTO(Price price);
}

package com.kairos.prueba.pricesapi.jpa.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {

    // Define el formato de fecha almacenado en la base de datos
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    // Convierte LocalDateTime a String para almacenar en BD
    @Override
    public String convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : locDateTime.format(FORMATTER));
    }

    // Convierte String de la BD a LocalDateTime en la entidad
    @Override
    public LocalDateTime convertToEntityAttribute(String sqlDate) {
        return (sqlDate == null ? null : LocalDateTime.parse(sqlDate, FORMATTER));
    }
}


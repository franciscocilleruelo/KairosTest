package com.kairos.prueba.pricesapi.jpa.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter class to handle the conversion between {@link LocalDateTime} and {@link String}
 * for database storage and retrieval.
 * <p>
 * This converter is used to store {@code LocalDateTime} as a formatted string in the database
 * and convert it back to a {@code LocalDateTime} object when retrieved. The format used
 * is {@code yyyy-MM-dd-HH.mm.ss}.
 * </p>
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {

    /**
     * Defines the date-time format to be used when storing {@code LocalDateTime} in the database.
     * The format used is {@code yyyy-MM-dd-HH.mm.ss}.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    /**
     * Converts a {@link LocalDateTime} object to a {@link String} to be stored in the database.
     *
     * @param locDateTime the {@code LocalDateTime} object to convert, or {@code null} if no value
     * @return a formatted string representing the {@code LocalDateTime} or {@code null} if input is null
     */
    @Override
    public String convertToDatabaseColumn(LocalDateTime locDateTime) {
        // Convert LocalDateTime to formatted string for database storage
        return (locDateTime == null ? null : locDateTime.format(FORMATTER));
    }

    /**
     * Converts a {@link String} from the database to a {@link LocalDateTime} object.
     *
     * @param sqlDate the string representation of the date-time from the database, or {@code null}
     * @return a {@code LocalDateTime} object parsed from the string, or {@code null} if input is null
     */
    @Override
    public LocalDateTime convertToEntityAttribute(String sqlDate) {
        // Convert the string from the database back to LocalDateTime
        return (sqlDate == null ? null : LocalDateTime.parse(sqlDate, FORMATTER));
    }
}

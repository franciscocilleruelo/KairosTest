package com.kairos.prueba.pricesapi.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the Price API.
 * <p>
 * This class is responsible for handling exceptions thrown by the REST controllers and 
 * providing custom responses in case of validation errors or type mismatches.
 * </p>
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles {@link MethodArgumentTypeMismatchException}, typically thrown when the format of a
     * parameter does not match the expected type (e.g., an invalid date format).
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} with a custom error message and HTTP 400 status
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        // Custom error message indicating the expected date format
        error.put("error", "Invalid date format for field '" + ex.getName() + "'. Expected format is 'yyyy-MM-ddTHH:mm:ss'.");

        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Handles {@link MethodArgumentNotValidException}, thrown when a validation constraint 
     * (e.g., @NotNull) is violated in a request DTO.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} with a map of validation error messages and HTTP 400 status
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        // Populate the error map with field-specific validation messages
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        
        return ResponseEntity.badRequest().body(errors);
    }
}

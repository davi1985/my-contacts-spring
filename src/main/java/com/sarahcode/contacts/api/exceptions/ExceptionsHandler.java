package com.sarahcode.contacts.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> categoryNotFound(CategoryNotFoundException exception) {
        Map<String, String> error = Map.of("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> Map.of(error.getField(), error.getDefaultMessage()))
            .toList();

        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }
}

package com.crm.crminventoryrepair.config;

import com.crm.crminventoryrepair.service.exception.DuplicateResourceException;
import com.crm.crminventoryrepair.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Globalny handler wyjątków dla warstwy REST.
 *
 * Przechwytuje wyjątki z serwisu/kontrolerów i zwraca spójne odpowiedzi JSON
 * z odpowiednim kodem HTTP i prostym ciałem {error, message}.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Obsługa duplikatów zasobów (np. próba stworzenia klienta z istniejącą nazwą firmy).
     * Zwraca status 409 Conflict.
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateResourceException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "duplicate");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Obsługa braku zasobu (np. klient o podanym ID nie istnieje).
     * Zwraca status 404 Not Found.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "not_found");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Obsługa błędów walidacji danych wejściowych (@Valid) i zwrócenie czytelnej listy błędów.
     * Zwraca status 400 Bad Request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        body.put("error", "validation");
        body.put("message", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Fallback dla nieobsłużonych wyjątków — zwraca ogólny błąd 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAny(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "internal");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
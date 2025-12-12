package com.crm.crminventoryrepair.service.exception;

/**
 * Wyjątek oznaczający brak zasobu.
 * Rzucany, gdy żądany obiekt nie został znaleziony (np. błędne ID).
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
package com.crm.crminventoryrepair.service.exeption;

/**
 * Wyjątek oznaczający próbę utworzenia/zmodyfikowania zasobu,
 * który już istnieje (naruszenie unikatowości).
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
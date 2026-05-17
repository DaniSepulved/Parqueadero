package com.example.Parqueadero.exception;

// Excepción personalizada para indicar que un recurso no fue encontrado.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
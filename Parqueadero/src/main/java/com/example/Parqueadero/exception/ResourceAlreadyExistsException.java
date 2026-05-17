package com.example.Parqueadero.exception;

// Excepción personalizada para indicar que un recurso ya existe.
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
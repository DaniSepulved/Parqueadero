package com.example.Parqueadero.exception;

// Excepción personalizada para errores de autenticación.
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}

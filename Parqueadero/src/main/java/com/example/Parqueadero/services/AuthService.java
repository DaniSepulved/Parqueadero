package com.example.Parqueadero.services;

import com.example.Parqueadero.model.Usuarios;

// Interfaz del servicio de autenticación, que define los métodos para el inicio de sesión y la autenticación de usuarios
public interface AuthService {
    Usuarios login(Usuarios usuarios);
    Usuarios authenticateUser(String email, String password);
}
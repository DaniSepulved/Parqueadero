package com.example.Parqueadero.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.exception.AuthenticationException;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.repository.UsuarioRepository;
import com.example.Parqueadero.services.AuthService;

// Implementación del servicio de autenticación, que maneja el proceso de inicio de sesión y validación de usuarios.
@Service
public class AuthServiceImpl implements AuthService {

    // Inyección de dependencias para el repositorio de usuarios y el codificador de contraseñas.
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Inyección de dependencias para el codificador de contraseñas, utilizado para comparar las contraseñas ingresadas con las almacenadas en la base de datos.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para iniciar sesión, que recibe un objeto Usuarios con las credenciales del usuario, 
    // valida la información y devuelve el usuario autenticado si las credenciales son correctas.
    @Override
    public Usuarios login(Usuarios usuarios) {
        Optional<Usuarios> optionalUser = usuarioRepository.findByEmail(usuarios.getEmail());

        if (optionalUser.isPresent()) {
            Usuarios usuariosLogin = optionalUser.get();
            if (passwordEncoder.matches(usuarios.getPassword(), usuariosLogin.getPassword())) {
                return usuariosLogin;
            }
        }

        throw new AuthenticationException("Email o contraseña incorrectos");
    }

    // Método para autenticar un usuario, utilizado internamente para validar las credenciales 
    // durante el proceso de inicio de sesión.
    @Override
    public Usuarios authenticateUser(String email, String password) {
        return usuarioRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Credenciales inválidas"));
    }
}
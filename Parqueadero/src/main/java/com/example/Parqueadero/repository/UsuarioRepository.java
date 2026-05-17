package com.example.Parqueadero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Parqueadero.model.Usuarios;

// Repositorio para la entidad Usuarios, que extiende JpaRepository para proporcionar operaciones CRUD.
public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

    // Método para encontrar un usuario por su correo electrónico (email).
    Optional<Usuarios> findByEmail(String email);
}
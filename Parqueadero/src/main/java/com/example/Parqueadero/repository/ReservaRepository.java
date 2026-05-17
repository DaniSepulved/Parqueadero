package com.example.Parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Parqueadero.model.Reservas;

// Repositorio para la entidad Reservas, que extiende JpaRepository para proporcionar operaciones CRUD.
public interface ReservaRepository extends JpaRepository<Reservas, Long> {
}
package com.example.Parqueadero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Parqueadero.model.Vehiculos;

// Repositorio para la entidad Vehiculos, que extiende JpaRepository para proporcionar operaciones CRUD.
public interface VehiculosRepository extends JpaRepository<Vehiculos, Long>{

    // Método para encontrar un vehículo por su placa.
    Optional<Vehiculos> findByPlaca(String placa); 
}
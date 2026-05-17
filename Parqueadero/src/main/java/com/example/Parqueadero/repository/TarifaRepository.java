package com.example.Parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Parqueadero.model.Tarifas;
import com.example.Parqueadero.model.TipoVehiculo;

import java.util.Optional;

// Repositorio para la entidad Tarifas, que extiende JpaRepository para proporcionar operaciones CRUD.
public interface TarifaRepository extends JpaRepository<Tarifas, Long> {

    // Método para encontrar una tarifa por el tipo de vehículo.
    Optional<Tarifas> findByTipoVehiculo(TipoVehiculo tipoVehiculo);
}
package com.example.Parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Parqueadero.model.EspaciosParqueo;

// Repositorio para la entidad EspaciosParqueo, que extiende JpaRepository para proporcionar operaciones CRUD.
public interface EspaciosParqueoRepository extends JpaRepository<EspaciosParqueo, Integer> {
    boolean existsByNumeroEspacio(Integer numeroEspacio);
}

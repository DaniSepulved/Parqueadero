package com.example.Parqueadero.services;

import java.util.List;

import com.example.Parqueadero.dto.VehiculosDTO;
import com.example.Parqueadero.model.Vehiculos;

// Interfaz del servicio para la gestión de vehículos, que define los métodos para crear, 
// actualizar, eliminar, listar y buscar vehículos por ID en el sistema
public interface VehiculosService {
    Vehiculos crear(VehiculosDTO dto);
    Vehiculos actualizar(Long id, VehiculosDTO dto);
    void eliminar(Long id);
    List<Vehiculos> listar();
    Vehiculos buscarPorId(Long id);
}
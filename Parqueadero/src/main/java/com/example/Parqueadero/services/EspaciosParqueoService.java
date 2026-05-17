package com.example.Parqueadero.services;

import java.util.List;

import com.example.Parqueadero.dto.EspaciosParqueoDTO;

// Interfaz del servicio para la gestión de espacios de parqueo, que define los métodos para 
// crear, actualizar, eliminar y listar los espacios de parqueo disponibles en el sistema
public interface EspaciosParqueoService {
    EspaciosParqueoDTO crearEspacio(EspaciosParqueoDTO dto);
    EspaciosParqueoDTO actualizarEspacio(Integer id, EspaciosParqueoDTO dto);
    void eliminarEspacio(Integer id);
    EspaciosParqueoDTO obtenerEspacioPorId(Integer id);
    List<EspaciosParqueoDTO> listarEspacios();
}
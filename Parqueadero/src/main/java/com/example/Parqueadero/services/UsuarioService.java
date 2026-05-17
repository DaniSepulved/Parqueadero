package com.example.Parqueadero.services;

import java.util.List;

import com.example.Parqueadero.dto.UsuarioDTO;
import com.example.Parqueadero.model.Usuarios;

// Interfaz del servicio para la gestión de usuarios, que define los métodos para crear, 
// listar, buscar por ID, actualizar y eliminar usuarios en el sistema
public interface UsuarioService {
    Usuarios crear(UsuarioDTO dto);
    List<Usuarios> listar();
    Usuarios buscarPorId(Long id);
    Usuarios actualizar(Long id, UsuarioDTO dto);
    void eliminar(Long id);
}

package com.example.Parqueadero.services;

import java.util.List;

import com.example.Parqueadero.dto.ReservaDTO;
import com.example.Parqueadero.model.Reservas;

// Interfaz del servicio para la gestión de reservas, que define los métodos para 
// crear, listar, obtener por ID y eliminar reservas en el sistema
public interface ReservaService {
    Reservas crearReserva(ReservaDTO dto);
    List<Reservas> listarReservas();
    Reservas obtenerReservaPorId(Long id);
    void eliminarReserva(Long id);
}
package com.example.Parqueadero.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Parqueadero.dto.TarifaDTO;
import com.example.Parqueadero.model.TipoVehiculo;

// Interfaz del servicio para la gestión de tarifas, que define los métodos para obtener 
// todas las tarifas, guardar una nueva tarifa y calcular el costo de una reserva en función del tipo de 
// vehículo y el tiempo de estacionamiento
public interface TarifaService {
    List<TarifaDTO> obtenerTodas();
    TarifaDTO guardarTarifa(TarifaDTO dto);
    Float calcularCosto(TipoVehiculo tipoVehiculo, LocalDateTime inicio, LocalDateTime fin);
}
package com.example.Parqueadero.controller;

import com.example.Parqueadero.dto.TarifaDTO;
import com.example.Parqueadero.model.TipoVehiculo;
import com.example.Parqueadero.services.TarifaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Controlador REST encargado de gestionar las tarifas del parqueadero.
// Expone endpoints para listar tarifas, crear nuevas tarifas y calcular el costo de una estadía en 
// base al tipo de vehículo y el tiempo de parqueo.
@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    // Lista todas las tarifas registradas.
    // @return Lista de tarifas
    @GetMapping
    public List<TarifaDTO> getAll() {
        return tarifaService.obtenerTodas();
    }

    // Crea una nueva tarifa.
    // @param dto Datos de la tarifa a crear
    @PostMapping
    public TarifaDTO save(@RequestBody TarifaDTO dto) {
        return tarifaService.guardarTarifa(dto);
    }

    // Calcula el costo de una estadía en base al tipo de vehículo y el tiempo de parqueo.
    // @param tipoVehiculo Tipo de vehículo
    // @param inicio Fecha y hora de inicio del parqueo
    // @param fin Fecha y hora de finalización del parqueo
    // @return Costo calculado
    @GetMapping("/calcular")
    public Float calcular(
            @RequestParam TipoVehiculo tipoVehiculo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin
    ) {
        return tarifaService.calcularCosto(tipoVehiculo, inicio, fin);
    }
}
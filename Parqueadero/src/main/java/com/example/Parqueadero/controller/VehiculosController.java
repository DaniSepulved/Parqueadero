package com.example.Parqueadero.controller;

import com.example.Parqueadero.dto.VehiculosDTO;
import com.example.Parqueadero.model.Vehiculos;
import com.example.Parqueadero.services.VehiculosService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST encargado de gestionar las operaciones CRUD de vehículos.
// Expone endpoints para crear, listar, buscar, actualizar y eliminar vehículos.
@RestController 
@RequestMapping("/api/vehiculos")
public class VehiculosController {

    private final VehiculosService vehiculosService;

    // Inyección de dependencias por constructor
    public VehiculosController(VehiculosService vehiculosService) {
        this.vehiculosService = vehiculosService;
    }

    // Crea un nuevo vehículo.
    // @param dto Datos del vehículo a crear
    @PostMapping
    public ResponseEntity<Vehiculos> crear(@Valid @RequestBody VehiculosDTO dto) {
        return ResponseEntity.ok(vehiculosService.crear(dto));
    }

    // Lista todos los vehículos registrados.
    // @return Lista de vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculos>> listar() {
        return ResponseEntity.ok(vehiculosService.listar());
    }

    // Busca un vehículo por su ID.
    // @param id Identificador del vehículo
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculos> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculosService.buscarPorId(id));
    }

    // Actualiza los datos de un vehículo.
    // @param id Identificador del vehículo a actualizar
    // @param dto Datos actualizados del vehículo
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculos> actualizar(@PathVariable Long id, @Valid @RequestBody VehiculosDTO dto) {
        return ResponseEntity.ok(vehiculosService.actualizar(id, dto));
    }

    // Elimina un vehículo por su ID.
    // @param id Identificador del vehículo a eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.Parqueadero.controller;

import com.example.Parqueadero.dto.ReservaDTO;
import com.example.Parqueadero.model.Reservas;
import com.example.Parqueadero.services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST encargado de gestionar las operaciones CRUD de reservas.
// Expone endpoints para crear, listar, buscar y eliminar reservas.
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Crea una nueva reserva.
    // @param dto Datos de la reserva a crear
    @PostMapping
    public ResponseEntity<Reservas> crearReserva(@RequestBody ReservaDTO dto) {
        return ResponseEntity.ok(reservaService.crearReserva(dto));
    }

    // Lista todas las reservas registradas.
    // @return Lista de reservas
    @GetMapping
    public ResponseEntity<List<Reservas>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    // Busca una reserva por su ID.
    // @param id Identificador de la reserva
    @GetMapping("/{id}")
    public ResponseEntity<Reservas> obtenerReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerReservaPorId(id));
    }

    // Elimina una reserva por su ID.
    // @param id Identificador de la reserva a eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
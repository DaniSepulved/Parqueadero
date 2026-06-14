package com.example.Parqueadero.controller;

import com.example.Parqueadero.dto.ReservaDTO;
import com.example.Parqueadero.model.Reservas;
import com.example.Parqueadero.services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO dto) {
        // 1. Llama al servicio común y corriente para que guarde en la BD
        Reservas reservaCreada = reservaService.crearReserva(dto);
        
        // 2. Construimos una respuesta limpia solo con los datos primitivos necesarios
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idReserva", reservaCreada.getIdReserva());
        respuesta.put("fechaReserva", reservaCreada.getFechaReserva());
        respuesta.put("mensaje", "Reserva creada con éxito en el backend");
        
        // 3. Retornamos el mapa plano
        return ResponseEntity.ok(respuesta);
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
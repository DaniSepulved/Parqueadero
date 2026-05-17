package com.example.Parqueadero.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Parqueadero.dto.EspaciosParqueoDTO;
import com.example.Parqueadero.services.EspaciosParqueoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

// Controlador REST encargado de gestionar las operaciones CRUD de espacios de parqueo.
// Expone endpoints para crear, listar, buscar, actualizar y eliminar espacios de parqueo.
@RestController
@RequestMapping("/api/espacios")
@RequiredArgsConstructor
public class EspaciosParqueoController {

    // Inyección de dependencias por constructor
    private final EspaciosParqueoService espaciosParqueoServiceservice;

    // Crea un nuevo espacio de parqueo.
    // @param dto Datos del espacio de parqueo a crear
    // @return Espacio de parqueo creado
    @PostMapping
    public ResponseEntity<EspaciosParqueoDTO> crear(@RequestBody EspaciosParqueoDTO dto) {
        return ResponseEntity.ok(espaciosParqueoServiceservice.crearEspacio(dto));
    }

    // Lista todos los espacios de parqueo registrados.
    // @return Lista de espacios de parqueo
    @PutMapping("/{id}")
    public ResponseEntity<EspaciosParqueoDTO> actualizar(@PathVariable Integer id,
                                                         @RequestBody EspaciosParqueoDTO dto) {
        return ResponseEntity.ok(espaciosParqueoServiceservice.actualizarEspacio(id, dto));
    }

    // Elimina un espacio de parqueo por su ID.
    // @param id Identificador del espacio de parqueo a eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        espaciosParqueoServiceservice.eliminarEspacio(id);
        return ResponseEntity.noContent().build();
    }

    // Busca un espacio de parqueo por su ID.
    // @param id Identificador del espacio de parqueo
    @GetMapping("/{id}")
    public ResponseEntity<EspaciosParqueoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(espaciosParqueoServiceservice.obtenerEspacioPorId(id));
    }

    // Lista todos los espacios de parqueo registrados.
    // @return Lista de espacios de parqueo
    @GetMapping
    public ResponseEntity<List<EspaciosParqueoDTO>> listar() {
        return ResponseEntity.ok(espaciosParqueoServiceservice.listarEspacios());
    }
}

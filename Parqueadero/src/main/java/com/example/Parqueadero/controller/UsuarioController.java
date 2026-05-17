package com.example.Parqueadero.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Parqueadero.dto.UsuarioDTO;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.services.UsuarioService;

import jakarta.validation.Valid;

// Controlador REST encargado de gestionar las operaciones CRUD de usuarios.
// Expone endpoints para crear, listar, buscar, actualizar y eliminar usuarios.
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    // Servicio que contiene la lógica de negocio
    private final UsuarioService usuarioService;

    // Inyección de dependencias por constructor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    
    // Crea un nuevo usuario.
    // @param dto Datos del usuario a crear
    // @return Usuario creado
    @PostMapping
    public ResponseEntity<Usuarios> crear(@Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.crear(dto));
    }

    
    // Lista todos los usuarios registrados.
    // @return Lista de usuarios
    @GetMapping
    public ResponseEntity<List<Usuarios>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    
    // Busca un usuario por su ID.
    // @param id Identificador del usuario
    // @return Usuario encontrado
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    
    // Actualiza los datos de un usuario existente.
    // @param id ID del usuario
    // @param dto Nuevos datos del usuario
    // @return Usuario actualizado
    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    
    // Elimina un usuario por su ID.
    // @param id ID del usuario a eliminar
    // @return Respuesta sin contenido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.Parqueadero.services.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.dto.UsuarioDTO;
import com.example.Parqueadero.model.Rol;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.repository.UsuarioRepository;
import com.example.Parqueadero.services.UsuarioService;

import jakarta.persistence.EntityNotFoundException;

// Implementación del servicio para la gestión de usuarios, que maneja las operaciones relacionadas con los usuarios del sistema
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor para la inyección de dependencias, que recibe el repositorio de usuarios y el 
    // codificador de contraseñas como parámetros.
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para crear un nuevo usuario, que recibe un objeto UsuarioDTO con los datos del usuario a crear
    @Override
    public Usuarios crear(UsuarioDTO dto) {
        Usuarios usuarios = Usuarios.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .rol(dto.getRol() != null ? dto.getRol() : Rol.CLIENTE)
                .build();

        return usuarioRepository.save(usuarios);
    }

    // Método para actualizar un usuario existente, que recibe el ID del usuario a actualizar y un objeto 
    // UsuarioDTO con los nuevos datos del usuario,
    @Override
    public Usuarios actualizar(Long id, UsuarioDTO dto) {
        Usuarios usuarios = buscarPorId(id);

        usuarios.setNombre(dto.getNombre());
        usuarios.setApellido(dto.getApellido());
        usuarios.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuarios.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        usuarios.setRol(dto.getRol());

        return usuarioRepository.save(usuarios);
    }

    // Método para eliminar un usuario por su ID, que utiliza el repositorio de usuarios para 
    // eliminar el registro de la base de datos.
    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método para listar todos los usuarios registrados, que utiliza el repositorio de usuarios para 
    // obtener la lista de usuarios desde la base de datos.
    @Override
    public List<Usuarios> listar() {
        return usuarioRepository.findAll();
    }

    // Método para buscar un usuario por su ID, que utiliza el repositorio de usuarios para 
    // buscar el registro en la base de datos y devuelve el usuario encontrado o lanza una
    @Override
    public Usuarios buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
    }
}
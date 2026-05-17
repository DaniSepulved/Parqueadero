package com.example.Parqueadero.dto;

import com.example.Parqueadero.model.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO utilizado para transferir datos
// del usuario entre el cliente y el servidor.
@Data
public class UsuarioDTO {

    // Nombre del usuario (obligatorio)
    @NotBlank
    private String nombre;

    // Apellido del usuario (obligatorio)
    @NotBlank
    private String apellido;

    // Correo electrónico válido
    @Email
    private String email;

    // Contraseña del usuario (obligatorio)
    @NotBlank
    private String password;

    // Rol del usuario (ADMIN, EMPLEADO, CLIENTE)
    private Rol rol;
}
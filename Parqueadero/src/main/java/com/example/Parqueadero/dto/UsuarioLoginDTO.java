package com.example.Parqueadero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para representar la información de inicio de sesión de un usuario.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {

    // Correo electrónico del usuario que intenta iniciar sesión.
    private String email;
    
    // Contraseña del usuario que intenta iniciar sesión.
    private String password;

    private String nombre;

    private String apellido;
}

package com.example.Parqueadero.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidad que representa a los usuarios del sistema, incluyendo sus datos personales y su rol (administrador o empleado).
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios {

    // Identificador único del usuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    // Nombre del usuario (obligatorio).
    @Column(nullable = false)
    private String nombre;

    // Apellido del usuario (obligatorio).
    @Column(nullable = false)
    private String apellido;

    // Correo electrónico del usuario (obligatorio y único).
    @Column(nullable = false, unique = true)
    private String email;

    // Contraseña del usuario (obligatoria).
    @Column(nullable = false)
    private String password;

    // Rol del usuario en el sistema (obligatorio, puede ser ADMIN o EMPLEADO).
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    // Relación con la entidad Vehiculos (un usuario puede tener varios vehículos).
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehiculos> vehiculos;

    // Relación con la entidad Reservas (un usuario puede tener varias reservas).
    public boolean isEmpty() {
        return this.nombre == null || this.nombre.isEmpty() ||
               this.apellido == null || this.apellido.isEmpty() ||
               this.email == null || this.email.isEmpty() ||
               this.password == null || this.password.isEmpty() ||
               this.rol == null;
    }

    // Constructor adicional para crear un usuario con solo email y password (útil para autenticación).
    public Usuarios(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
package com.example.Parqueadero.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidad que representa los vehículos registrados en el sistema.
@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculos {

    // Identificador único del vehículo.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    // Placa del vehículo (obligatoria y única).
    @Column(nullable = false, unique = true)
    private String placa;

    // Color del vehículo (obligatorio).
    @Column(nullable = false)
    private String color;

    // Modelo del vehículo (obligatorio).
    @Column(nullable = false)
    private String modelo;

    // Marca del vehículo (obligatoria).
    @Column(nullable = false)
    private String marca;

    // Tipo de vehículo (obligatorio, puede ser CARRO, MOTO o CAMION).
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVehiculo tipoVehiculo;

    // Relación con la entidad Usuarios (muchos vehículos pueden pertenecer a un usuario).
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuario;

    // Método para verificar si el vehículo tiene campos vacíos o nulos (útil para validaciones).
    public boolean isEmpty() {
        return this.placa == null || this.placa.isEmpty() ||
               this.color == null || this.color.isEmpty() ||
               this.marca == null || this.marca.isEmpty() ||
               this.tipoVehiculo == null;
    }
}
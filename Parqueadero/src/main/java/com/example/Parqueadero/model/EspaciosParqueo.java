package com.example.Parqueadero.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidad que representa los espacios de parqueo disponibles en el sistema.
@Entity
@Table(name = "espaciosparqueo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspaciosParqueo {
    // Identificador único del espacio de parqueo.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspacio")
    private Integer idEspacio;

    // Ubicación del espacio de parqueo (ejemplo: "Nivel 1 - Espacio 5").
    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    // Número del espacio de parqueo (único y obligatorio).
    @Column(name = "numeroEspacio", nullable = false, unique = true)
    private Integer numeroEspacio;
}
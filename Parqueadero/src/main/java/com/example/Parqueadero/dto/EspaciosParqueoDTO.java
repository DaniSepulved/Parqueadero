package com.example.Parqueadero.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO para representar los espacios de parqueo disponibles.
@Data
public class EspaciosParqueoDTO {

    // Identificador único del espacio de parqueo.
    private Integer idEspacio;

    // Ubicación del espacio de parqueo dentro del parqueadero (ejemplo: "Nivel 1 - Zona A").
    @NotBlank
    private String ubicacion;

    // Número del espacio de parqueo.
    @NotNull
    private Integer numeroEspacio;
}

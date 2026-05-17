package com.example.Parqueadero.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para la creación y actualización de categorías de vehículos.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    // El nombre de la categoría es obligatorio y no puede exceder los 50 caracteres.
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String name;

    // La descripción de la categoría es opcional y no puede exceder los 255 caracteres.
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String description;  
}
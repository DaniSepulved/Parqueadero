package com.example.Parqueadero.dto;

import com.example.Parqueadero.model.TipoVehiculo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para representar la información de una tarifa de parqueo.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarifaDTO {

    // Identificador único de la tarifa.
    @NotBlank
    private Long idTarifa;

    // Tipo de vehículo al que se aplica la tarifa (ejemplo: "Automóvil", "Motocicleta", "Bicicleta").
    @NotBlank
    private TipoVehiculo tipoVehiculo;

    // Costo por hora de parqueo para el tipo de vehículo especificado.
    @NotBlank
    private Float costoHora;

    // Costo por día de parqueo para el tipo de vehículo especificado.
    @NotBlank
    private Float costoDia;
}
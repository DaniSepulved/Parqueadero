package com.example.Parqueadero.dto;

import com.example.Parqueadero.model.TipoVehiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO utilizado para transferir datos
// del vehículo entre el cliente y el servidor.
@Data
public class VehiculosDTO {

    // Placa del vehículo (obligatorio)
    @NotBlank
    private String placa;

    // Color del vehículo
    @NotBlank
    private String color;

    // Marca del vehículo
    @NotBlank
    private String marca;

    // Tipo de vehículo (obligatorio)
    @NotNull
    private TipoVehiculo tipoVehiculo;

    // ID del usuario propietario del vehículo (obligatorio)
    @NotNull
    private Long idUsuario;
}


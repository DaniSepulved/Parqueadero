package com.example.Parqueadero.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO para representar la información de una reserva de parqueo.
@Data
public class ReservaDTO {

    // Identificador único de la reserva.
    @NotNull
    private Long idReserva;

    // Identificador del usuario que realizó la reserva.
    @NotNull
    private Long idUsuario;

    // Identificador del espacio de parqueo reservado.
    @NotNull
    private Long idTarifa;

    // Identificador del usuario que realizó la reserva.
    @NotNull
    private LocalDate fechaReserva;

    // Hora de inicio de la reserva.
    @NotNull
    private LocalDateTime horaInicio;

    // Hora de fin de la reserva.
    @NotNull
    private LocalDateTime horaFin;
}
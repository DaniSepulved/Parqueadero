package com.example.Parqueadero.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {

    @NotNull
    private Long idPago;
 
    @NotNull
    private Long idReserva;
 
    @NotNull
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El monto no puede tener más de 10 dígitos en total y 2 decimales")
    private Double monto;

    @NotNull
    private LocalDate fechaPago;

    @NotBlank
    @Size(max = 50)
    private String metodoPago;
}

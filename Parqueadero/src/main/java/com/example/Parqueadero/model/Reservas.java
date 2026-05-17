package com.example.Parqueadero.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidad que representa las reservas realizadas por los usuarios para los espacios de parqueo.
@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservas {

    // Identificador único de la reserva.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Long idReserva;

    // Relación con la entidad Usuarios (muchas reservas pueden pertenecer a un usuario).
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuarios usuarios;

    // Relación con la entidad EspaciosParqueo (cada reserva está asociada a un espacio de parqueo).
    @ManyToOne
    @JoinColumn(name = "idEspacio", nullable = false)
    private EspaciosParqueo espaciosParqueo;

    // Relación con la entidad Tarifas (cada reserva tiene una tarifa asociada).
    @ManyToOne
    @JoinColumn(name = "idTarifa", nullable = false)
    private Tarifas tarifas;

    // Fecha de la reserva.
    @Column(name = "fechaReserva")
    private LocalDate fechaReserva;

    // Hora de inicio de la reserva.
    @Column(name = "horaInicio")
    private LocalDateTime horaInicio;

    // Hora de fin de la reserva.
    @Column(name = "horaFin")
    private LocalDateTime horaFin;

    // Relación con la entidad Pago (cada reserva tiene un pago asociado).
    // @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    // private Pago pago;
}

package com.example.Parqueadero.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "idReserva", nullable = false)
    private Reservas reservas;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fechaPago")
    private java.time.LocalDate fechaPago;

    @Column(name = "metodoPago")
    private String metodoPago;

    public Pagos(Reservas reservas, Double monto, LocalDate fechaPago, String metodoPago) {
        this.reservas = reservas;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }
}

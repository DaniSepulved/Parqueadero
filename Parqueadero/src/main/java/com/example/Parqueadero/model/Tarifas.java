package com.example.Parqueadero.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entidad que representa las tarifas de parqueo para diferentes tipos de vehículos.
@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifas {

    // Identificador único de la tarifa.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTarifa")
    private Long idTarifa;

    // Tipo de vehículo al que se aplica la tarifa (ejemplo: "Carro", "Moto", "Camión").
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoVehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;

    // Costo por hora de parqueo para el tipo de vehículo.
    @Column(name = "costoHora")
    private Float costoHora;

    // Costo por día de parqueo para el tipo de vehículo.
    @Column(name = "costoDia")
    private Float costoDia;

    // Relación con la entidad Reservas (una tarifa puede estar asociada a muchas reservas).
    @OneToMany(mappedBy = "tarifas")
    private List<Reservas> reservas;
}
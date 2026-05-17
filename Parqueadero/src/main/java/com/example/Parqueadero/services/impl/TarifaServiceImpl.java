package com.example.Parqueadero.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.dto.TarifaDTO;
import com.example.Parqueadero.model.Tarifas;
import com.example.Parqueadero.model.TipoVehiculo;
import com.example.Parqueadero.repository.TarifaRepository;
import com.example.Parqueadero.services.TarifaService;

// Implementación del servicio para la gestión de tarifas, que maneja las 
// operaciones relacionadas con las tarifas del parqueadero,
@Service
public class TarifaServiceImpl implements TarifaService {

    // Inyección de dependencias para el repositorio de tarifas, utilizado 
    // para acceder a la base de datos y realizar las operaciones necesarias para gestionar las tarifas.
    @Autowired
    private TarifaRepository tarifaRepository;

    @Override
    public List<TarifaDTO> obtenerTodas() {
        return tarifaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Método para guardar una nueva tarifa, que recibe un objeto TarifaDTO con los datos de la tarifa a crear,
    @Override
    public TarifaDTO guardarTarifa(TarifaDTO dto) {
        Tarifas tarifa = Tarifas.builder()
                .tipoVehiculo(dto.getTipoVehiculo())
                .costoHora(dto.getCostoHora())
                .costoDia(dto.getCostoDia())
                .build();
        return toDTO(tarifaRepository.save(tarifa));
    }

    // Método para calcular el costo de una estadía en base al tipo de vehículo y el tiempo de parqueo,
    @Override
    public Float calcularCosto(TipoVehiculo tipoVehiculo, LocalDateTime inicio, LocalDateTime fin) {
        Tarifas tarifa = tarifaRepository.findByTipoVehiculo(tipoVehiculo)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada para: " + tipoVehiculo));

        long horas = ChronoUnit.HOURS.between(inicio, fin);
        long dias = ChronoUnit.DAYS.between(inicio.toLocalDate(), fin.toLocalDate());

        if (horas >= 8 || dias >= 1) {
            return tarifa.getCostoDia() * (dias > 0 ? dias : 1);
        } else {
            return tarifa.getCostoHora() * (horas == 0 ? 1 : horas);
        }
    }

    // Método privado para convertir una entidad Tarifas a un objeto TarifaDTO, utilizado internamente para 
    // mapear los datos de la base de datos a un formato adecuado para la transferencia de datos.
    private TarifaDTO toDTO(Tarifas tarifa) {
        return TarifaDTO.builder()
                .idTarifa(tarifa.getIdTarifa())
                .tipoVehiculo(tarifa.getTipoVehiculo())
                .costoHora(tarifa.getCostoHora())
                .costoDia(tarifa.getCostoDia())
                .build();
    }
}
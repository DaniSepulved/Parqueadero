package com.example.Parqueadero.services;

import java.time.LocalDate;
import java.util.List;

import com.example.Parqueadero.dto.PagosDTO;

public interface PagosService {
    PagosDTO crearPago(PagosDTO pagosDTO);
    PagosDTO obtenerPagoPorId(Long idPago);
    List<PagosDTO> obtenerTodosLosPagos();
    List<PagosDTO> obtenerPagosPorReserva(Long idReserva);
    List<PagosDTO> obtenerPagosPorMetodo(String metodoPago);
    List<PagosDTO> obtenerPagosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    List<PagosDTO> obtenerPagosPorUsuario(Long idUsuario);
    PagosDTO actualizarPago(Long idPago, PagosDTO pagosDTO);
    void eliminarPago(Long idPago);
}

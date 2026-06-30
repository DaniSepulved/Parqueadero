package com.example.Parqueadero.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Parqueadero.dto.PagosDTO;
import com.example.Parqueadero.model.Pagos;
import com.example.Parqueadero.model.Reservas;
import com.example.Parqueadero.repository.PagosRepository;
import com.example.Parqueadero.repository.ReservaRepository;
import com.example.Parqueadero.services.PagosService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final ReservaRepository reservaRepository;
    private final ReservaServiceImpl reservaService;

    private PagosDTO toDTO(Pagos pagos) {
    return new PagosDTO(
            pagos.getIdPago(),
            pagos.getReservas().getIdReserva(),
            pagos.getMonto(),
            pagos.getFechaPago(),
            pagos.getMetodoPago()
        );
    }
 
    private Pagos toEntity(PagosDTO dto) {
        Reservas reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new RuntimeException(
                        "Reserva no encontrada con ID: " + dto.getIdReserva()));
 
        Pagos pagos = new Pagos();
        pagos.setReservas(reserva);
        double montoCalculado = reservaService.calcularTotalEstadia(reserva);
        pagos.setMonto(montoCalculado);

        pagos.setFechaPago(dto.getFechaPago() != null ? dto.getFechaPago() : LocalDate.now());

        pagos.setMetodoPago(dto.getMetodoPago());
        return pagos;
    }
    
        // ─── CRUD ───
 
    @Override
    public PagosDTO crearPago(PagosDTO pagoDTO) {
        if (pagosRepository.existsByReservas_IdReserva(pagoDTO.getIdReserva())) {
            throw new RuntimeException(
                    "Ya existe un pago registrado para la reserva ID: " + pagoDTO.getIdReserva());
        }
        Pagos pago = toEntity(pagoDTO);
        return toDTO(pagosRepository.save(pago));
    }
 
    @Override
    @Transactional(readOnly = true)
    public PagosDTO obtenerPagoPorId(Long idPago) {
        Pagos pago = pagosRepository.findById(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + idPago));
        return toDTO(pago);
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<PagosDTO> obtenerTodosLosPagos() {
        return pagosRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<PagosDTO> obtenerPagosPorReserva(Long idReserva) {
        return pagosRepository.findByReservas_IdReserva(idReserva)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<PagosDTO> obtenerPagosPorMetodo(String metodoPago) {
        return pagosRepository.findByMetodoPago(metodoPago)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<PagosDTO> obtenerPagosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return pagosRepository.findByFechaPagoBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<PagosDTO> obtenerPagosPorUsuario(Long idUsuario) {
        return pagosRepository.findByReservas_Usuarios_IdUsuario(idUsuario)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public PagosDTO actualizarPago(Long idPago, PagosDTO pagoDTO) {
        Pagos pagoExistente = pagosRepository.findById(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + idPago));
 
        // Si cambia la reserva, validar que la nueva no tenga otro pago
        if (!pagoExistente.getReservas().getIdReserva().equals(pagoDTO.getIdReserva())) {
            if (pagosRepository.existsByReservas_IdReserva(pagoDTO.getIdReserva())) {
                throw new RuntimeException(
                        "Ya existe un pago para la reserva ID: " + pagoDTO.getIdReserva());
            }
            Reservas nuevaReserva = reservaRepository.findById(pagoDTO.getIdReserva())
                    .orElseThrow(() -> new RuntimeException(
                            "Reserva no encontrada con ID: " + pagoDTO.getIdReserva()));
            pagoExistente.setReservas(nuevaReserva);
        }
 
        pagoExistente.setMonto(pagoDTO.getMonto());
        pagoExistente.setFechaPago(pagoDTO.getFechaPago());
        pagoExistente.setMetodoPago(pagoDTO.getMetodoPago());
 
        return toDTO(pagosRepository.save(pagoExistente));
    }
 
    @Override
    public void eliminarPago(Long idPago) {
        if (!pagosRepository.existsById(idPago)) {
            throw new RuntimeException("Pago no encontrado con ID: " + idPago);
        }
        pagosRepository.deleteById(idPago);
    }
}

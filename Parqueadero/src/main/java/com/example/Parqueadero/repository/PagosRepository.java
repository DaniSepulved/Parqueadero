package com.example.Parqueadero.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.Parqueadero.model.Pagos;

public interface PagosRepository extends JpaRepository<Pagos, Long>{
 
    // Buscar todos los pagos de una reserva
    List<Pagos> findByReservas_IdReserva(Long idReserva);
 
    // Buscar pagos por método de pago
    List<Pagos> findByMetodoPago(String metodoPago);
 
    // Buscar pagos por fecha exacta
    List<Pagos> findByFechaPago(LocalDate fechaPago);
 
    // Buscar pagos en un rango de fechas
    List<Pagos> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);
 
    // Buscar pagos de un usuario específico a través de la reserva
    List<Pagos> findByReservas_Usuarios_IdUsuario(@Param("idUsuario") Long idUsuario);
 
    // Verificar si ya existe un pago para una reserva
    boolean existsByReservas_IdReserva(Long idReserva);
}

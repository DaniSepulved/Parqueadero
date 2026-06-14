package com.example.Parqueadero.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Parqueadero.dto.PagosDTO;
import com.example.Parqueadero.services.PagosService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    @Autowired
    private PagosService pagosService;

     // POST /api/pagos
    @PostMapping
    public ResponseEntity<PagosDTO> crearPago(@Valid @RequestBody PagosDTO pagosDTO) {
        PagosDTO nuevoPago = pagosService.crearPago(pagosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

     // GET /api/pagos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PagosDTO> obtenerPagoPorId(@PathVariable Long idPago) {
        PagosDTO pagos = pagosService.obtenerPagoPorId(idPago);
        return ResponseEntity.ok(pagos);
    }

    // GET /api/pagos
    @GetMapping
    public ResponseEntity<List<PagosDTO>> obtenerTodosLosPagos() {
        List<PagosDTO> pagos = pagosService.obtenerTodosLosPagos();
        return ResponseEntity.ok(pagos);
    }

    // GET /api/pagos/reserva/{idReserva}
    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<List<PagosDTO>> obtenerPagosPorReserva(@PathVariable Long idReserva) {
        List<PagosDTO> pagos = pagosService.obtenerPagosPorReserva(idReserva);
        return ResponseEntity.ok(pagos);
    }

    // GET /api/pagos/metodo?metodoPago=Efectivo
    @GetMapping("/metodo")
    public ResponseEntity<List<PagosDTO>> obtenerPagosPorMetodo(
            @RequestParam String metodoPago) {
        List<PagosDTO> pagos = pagosService.obtenerPagosPorMetodo(metodoPago);
        return ResponseEntity.ok(pagos);
    }

    // GET /api/pagos/fechas?inicio=2024-01-01&fin=2024-12-31
    @GetMapping("/fechas")
    public ResponseEntity<List<PagosDTO>> obtenerPagosPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<PagosDTO> pagos = pagosService.obtenerPagosPorRangoFechas(inicio, fin);
        return ResponseEntity.ok(pagos);
    }
 
    // GET /api/pagos/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PagosDTO>> obtenerPagosPorUsuario(@PathVariable Long idUsuario) {
        List<PagosDTO> pagos = pagosService.obtenerPagosPorUsuario(idUsuario);
        return ResponseEntity.ok(pagos);
    }
 
    // PUT /api/pagos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PagosDTO> actualizarPago(
            @PathVariable Long idPago,
            @Valid @RequestBody PagosDTO pagosDTO) {
        PagosDTO pagosActualizado = pagosService.actualizarPago(idPago, pagosDTO);
        return ResponseEntity.ok(pagosActualizado);
    }
 
    // DELETE /api/pagos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long idPago) {
        pagosService.eliminarPago(idPago);
        return ResponseEntity.noContent().build();
    }
}

package com.example.Parqueadero.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.dto.ReservaDTO;
import com.example.Parqueadero.model.EspaciosParqueo;
import com.example.Parqueadero.model.Reservas;
import com.example.Parqueadero.model.Tarifas;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.repository.EspaciosParqueoRepository;
import com.example.Parqueadero.repository.ReservaRepository;
import com.example.Parqueadero.repository.TarifaRepository;
import com.example.Parqueadero.repository.UsuarioRepository;
import com.example.Parqueadero.services.ReservaService;

// Implementación del servicio para la gestión de reservas, que maneja las operaciones CRUD relacionadas con las reservas.
@Service
public class ReservaServiceImpl implements ReservaService {

    // Inyección de dependencias para los repositorios de reservas, usuarios y tarifas, 
    // que se utilizan para acceder a la base de datos y realizar las operaciones necesarias para gestionar las reservas.
    @Autowired
    private ReservaRepository reservaRepository;

    // Inyección de dependencias para el repositorio de usuarios, utilizado para buscar el usuario asociado a una reserva.
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Inyección de dependencias para el repositorio de tarifas, utilizado para buscar la tarifa asociada a una reserva.
    @Autowired
    private TarifaRepository tarifaRepository;


    @Autowired
    private EspaciosParqueoRepository espaciosparqueorepository;

    // Método para crear una nueva reserva, que recibe un objeto ReservaDTO con los datos de la reserva a crear,
    // valida la información y guarda la reserva en la base de datos, devolviendo la reserva
    @Override
    public Reservas crearReserva(ReservaDTO dto) {
        Usuarios usuario = usuarioRepository.findById(dto.getIdUsuario().longValue())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Tarifas tarifa = tarifaRepository.findById(dto.getIdTarifa().longValue())
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));

        EspaciosParqueo espacio = espaciosparqueorepository.findById(dto.getIdEspacio().intValue())
            .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

        Reservas reserva = Reservas.builder()
            .usuarios(usuario)
            .tarifas(tarifa)
            .espaciosParqueo(espacio)
            .fechaReserva(dto.getFechaReserva())
            .horaInicio(dto.getHoraInicio())
            .horaFin(dto.getHoraFin())
            .build();

        Reservas reservaGuardada = reservaRepository.save(reserva);

        // Aquí podrías enviar la impresión
        // System.out.println("🧾 Reserva creada: " + reservaGuardada); 

        return reservaGuardada;
    }

    // Método para listar todas las reservas registradas, que utiliza el repositorio de reservas para 
    // obtener la lista de reservas desde la base de datos.
    @Override
    public List<Reservas> listarReservas() {
        return reservaRepository.findAll();
    }

    // Método para obtener una reserva por su ID, que utiliza el repositorio de reservas para 
    // buscar el registro en la base de datos.
    @Override
    public Reservas obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    // Método para eliminar una reserva por su ID, que utiliza el repositorio 
    // de reservas para eliminar el registro de la base de datos.
    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
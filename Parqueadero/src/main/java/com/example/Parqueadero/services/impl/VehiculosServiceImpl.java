package com.example.Parqueadero.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.dto.VehiculosDTO;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.model.Vehiculos;
import com.example.Parqueadero.repository.UsuarioRepository;
import com.example.Parqueadero.repository.VehiculosRepository;
import com.example.Parqueadero.services.VehiculosService;

import java.util.List;

// Implementación del servicio para la gestión de vehículos, que maneja las operaciones relacionadas 
// con los vehículos registrados en el sistema
@Service
public class VehiculosServiceImpl implements VehiculosService {

    private final VehiculosRepository vehiculosRepository;
    private final UsuarioRepository usuarioRepository;

    // Constructor para la inyección de dependencias, que recibe el repositorio de vehículos y el
    public VehiculosServiceImpl(VehiculosRepository vehiculosRepository, UsuarioRepository usuarioRepository) {
        this.vehiculosRepository = vehiculosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Método para crear un nuevo vehículo, que recibe un objeto VehiculosDTO con los datos del vehículo a crear,
    // valida la información y guarda el vehículo en la base de datos, devolviendo el vehículo
    @Override
    public Vehiculos crear(VehiculosDTO dto) {
        Usuarios usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Vehiculos vehiculo = Vehiculos.builder()
                .placa(dto.getPlaca())
                .color(dto.getColor())
                .marca(dto.getMarca())
                .tipoVehiculo(dto.getTipoVehiculo())
                .usuario(usuario)
                .build();

        return vehiculosRepository.save(vehiculo);
    }

    // Método para actualizar un vehículo existente, que recibe el ID del vehículo a actualizar y un objeto 
    // VehiculosDTO con los nuevos datos del vehículo, valida la información y actualiza el
    @Override
    public Vehiculos actualizar(Long id, VehiculosDTO dto) {
        Vehiculos vehiculo = buscarPorId(id);

        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setColor(dto.getColor());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());

        if (dto.getIdUsuario() != null) {
            Usuarios usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
            vehiculo.setUsuario(usuario);
        }

        return vehiculosRepository.save(vehiculo);
    }

    // Método para eliminar un vehículo por su ID, que utiliza el repositorio de vehículos para 
    // eliminar el registro de la base de datos.
    @Override
    public void eliminar(Long id) {
        vehiculosRepository.deleteById(id);
    }

    // Método para listar todos los vehículos registrados, que utiliza el repositorio de vehículos para 
    // obtener la lista de vehículos desde la base de datos.
    @Override
    public List<Vehiculos> listar() {
        return vehiculosRepository.findAll();
    }

    // Método para buscar un vehículo por su ID, que utiliza el repositorio de vehículos para 
    // buscar el registro en la base de datos y devuelve el vehículo encontrado o lanza una excepción 
    // si no se encuentra el vehículo.
    @Override
    public Vehiculos buscarPorId(Long id) {
        return vehiculosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con ID: " + id));
    }
}
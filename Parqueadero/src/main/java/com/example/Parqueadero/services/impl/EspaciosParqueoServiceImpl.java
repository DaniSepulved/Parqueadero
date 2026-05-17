package com.example.Parqueadero.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.Parqueadero.dto.EspaciosParqueoDTO;
import com.example.Parqueadero.model.EspaciosParqueo;
import com.example.Parqueadero.repository.EspaciosParqueoRepository;
import com.example.Parqueadero.services.EspaciosParqueoService;

import java.util.List;
import java.util.stream.Collectors;

// Implementación del servicio para la gestión de espacios de parqueo, 
// que maneja las operaciones CRUD relacionadas con los espacios de parqueo.
@Service
@RequiredArgsConstructor
public class EspaciosParqueoServiceImpl implements EspaciosParqueoService {

    private final EspaciosParqueoRepository espaciosparqueorepository;

    // Método para crear un nuevo espacio de parqueo, que verifica si el 
    // número de espacio ya existe antes de guardarlo en la base de datos.
    @Override
    public EspaciosParqueoDTO crearEspacio(EspaciosParqueoDTO dto) {
        if (espaciosparqueorepository.existsByNumeroEspacio(dto.getNumeroEspacio())) {
            throw new RuntimeException("El número de espacio ya existe");
        }
        EspaciosParqueo espacio = mapToEntity(dto);
        return mapToDTO(espaciosparqueorepository.save(espacio));
    }

    // Método para actualizar un espacio de parqueo existente, que busca el espacio por su ID
    @Override
    public EspaciosParqueoDTO actualizarEspacio(Integer id, EspaciosParqueoDTO dto) {
        EspaciosParqueo espacio = espaciosparqueorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));

        espacio.setUbicacion(dto.getUbicacion());
        espacio.setNumeroEspacio(dto.getNumeroEspacio());

        return mapToDTO(espaciosparqueorepository.save(espacio));
    }

    // Método para eliminar un espacio de parqueo por su ID, que utiliza el repositorio 
    // para eliminar el registro de la base de datos.
    @Override
    public void eliminarEspacio(Integer id) {
        espaciosparqueorepository.deleteById(id);
    }

    // Método para obtener un espacio de parqueo por su ID, que utiliza el repositorio 
    // para buscar el registro en la base de datos.
    @Override
    public EspaciosParqueoDTO obtenerEspacioPorId(Integer id) {
        EspaciosParqueo espacio = espaciosparqueorepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));
        return mapToDTO(espacio);
    }

    // Método para listar todos los espacios de parqueo registrados, que utiliza el repositorio
    @Override
    public List<EspaciosParqueoDTO> listarEspacios() {
        return espaciosparqueorepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares para mapear entre la entidad EspaciosParqueo y el DTO EspaciosParqueoDTO,
    // facilitando la conversión de datos entre las capas de la aplicación.
    private EspaciosParqueo mapToEntity(EspaciosParqueoDTO dto) {
        return EspaciosParqueo.builder()
                .idEspacio(dto.getIdEspacio())
                .ubicacion(dto.getUbicacion())
                .numeroEspacio(dto.getNumeroEspacio())
                .build();
    }

    // Método auxiliar para mapear una entidad EspaciosParqueo a un DTO EspaciosParqueoDTO,
    private EspaciosParqueoDTO mapToDTO(EspaciosParqueo espacio) {
        EspaciosParqueoDTO dto = new EspaciosParqueoDTO();
        dto.setIdEspacio(espacio.getIdEspacio());
        dto.setUbicacion(espacio.getUbicacion());
        dto.setNumeroEspacio(espacio.getNumeroEspacio());
        return dto;
    }
}

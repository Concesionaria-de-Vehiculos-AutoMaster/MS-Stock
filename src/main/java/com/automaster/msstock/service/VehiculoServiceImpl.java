package com.automaster.msstock.service;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;
import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.automaster.msstock.client.ModeloClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ModeloClient modeloClient;

    @Override
    public List<VehiculoResponseDTO> listarTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculoResponseDTO guardar(VehiculoRequestDTO vehiculoRequestDTO) {
        // 1. VALIDACIÓN CRUZADA: Preguntamos a MS-Modelos si existe el ID
        boolean modeloExiste = modeloClient.existeModelo(vehiculoRequestDTO.getIdModelo());

        if (!modeloExiste) {
            // Si no existe, detenemos todo y lanzamos un error
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede guardar: El modelo con ID " + vehiculoRequestDTO.getIdModelo() + " no existe en la base de datos de modelos.");
        }

        // 2. Si existe, procedemos a guardar el vehículo normalmente
        Vehiculo vehiculo = convertirAEntidad(vehiculoRequestDTO);
        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);
        return convertirAResponseDTO(vehiculoGuardado);
    }

    private VehiculoResponseDTO convertirAResponseDTO(Vehiculo vehiculo) {
        VehiculoResponseDTO dto = new VehiculoResponseDTO();
        dto.setIdVehiculo(vehiculo.getIdVehiculo());
        dto.setVin(vehiculo.getVin());
        dto.setIdModelo(vehiculo.getIdModelo());
        dto.setCondicion(vehiculo.getCondicion());
        dto.setKilometraje(vehiculo.getKilometraje());
        dto.setPrecioVenta(vehiculo.getPrecioVenta());
        dto.setEstadoDisponibilidad(vehiculo.getEstadoDisponibilidad());
        return dto;
    }

    private Vehiculo convertirAEntidad(VehiculoRequestDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setVin(dto.getVin());
        vehiculo.setIdModelo(dto.getIdModelo());
        vehiculo.setCondicion(dto.getCondicion());
        vehiculo.setKilometraje(dto.getKilometraje());
        vehiculo.setPrecioVenta(dto.getPrecioVenta());
        vehiculo.setEstadoDisponibilidad(dto.getEstadoDisponibilidad());
        return vehiculo;
    }
}
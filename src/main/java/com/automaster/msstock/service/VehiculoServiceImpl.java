package com.automaster.msstock.service;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;
import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<VehiculoResponseDTO> listarTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculoResponseDTO guardar(VehiculoRequestDTO vehiculoRequestDTO) {
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
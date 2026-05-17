package com.automaster.msstock.service;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;

public interface VehiculoService {
    VehiculoResponseDTO guardarVehiculo(VehiculoRequestDTO request);
    VehiculoResponseDTO buscarPorId(Long id);
}
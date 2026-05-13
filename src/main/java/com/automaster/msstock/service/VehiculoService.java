package com.automaster.msstock.service;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;
import java.util.List;

public interface VehiculoService {
    List<VehiculoResponseDTO> listarTodos();
    VehiculoResponseDTO guardar(VehiculoRequestDTO vehiculoRequestDTO);
}
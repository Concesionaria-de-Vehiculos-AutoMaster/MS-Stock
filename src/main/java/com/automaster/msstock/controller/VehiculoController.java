package com.automaster.msstock.controller;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;
import com.automaster.msstock.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stock")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> listarStock() {
        return new ResponseEntity<>(vehiculoService.listarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> registrarVehiculo(@Valid @RequestBody VehiculoRequestDTO vehiculoRequestDTO) {
        VehiculoResponseDTO nuevoVehiculo = vehiculoService.guardar(vehiculoRequestDTO);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }
}
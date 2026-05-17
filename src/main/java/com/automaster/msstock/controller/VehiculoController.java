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

@Slf4j
@RestController
@RequestMapping("/api/stock")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> registrarVehiculo(@Valid @RequestBody VehiculoRequestDTO request) {
        log.info("Petición REST POST recibida para registrar vehículo");
        VehiculoResponseDTO response = vehiculoService.guardarVehiculo(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerVehiculo(@PathVariable Long id) {
        log.info("Petición REST GET recibida para obtener vehículo por ID");
        VehiculoResponseDTO response = vehiculoService.buscarPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
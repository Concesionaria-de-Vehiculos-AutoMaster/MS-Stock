package com.automaster.msstock.service;

import com.automaster.msstock.dto.VehiculoRequestDTO;
import com.automaster.msstock.dto.VehiculoResponseDTO;
import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public VehiculoResponseDTO guardarVehiculo(VehiculoRequestDTO request) {
        log.info("Iniciando guardado de vehículo VIN: {}", request.getVin());

        if (vehiculoRepository.existsByVin(request.getVin())) {
            log.error("El VIN {} ya existe en el inventario", request.getVin());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vehículo con este VIN ya está registrado");
        }

        // VALIDACIÓN CON WEBCLIENT HACIA MS-MODELOS (PUERTO 8081)
        validarModeloExiste(request.getIdModelo());

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setVin(request.getVin());
        vehiculo.setIdModelo(request.getIdModelo());
        vehiculo.setCondicion(request.getCondicion());
        vehiculo.setKilometraje(request.getKilometraje());
        vehiculo.setPrecioVenta(request.getPrecioVenta());
        vehiculo.setEstadoDisponibilidad("Disponible"); // Estado inicial por defecto

        Vehiculo guardado = vehiculoRepository.save(vehiculo);
        log.info("Vehículo guardado exitosamente con ID: {}", guardado.getId());

        return mapearADTO(guardado);
    }

    @Override
    public VehiculoResponseDTO buscarPorId(Long id) {
        log.info("Buscando vehículo por ID: {}", id);
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Vehículo ID {} no encontrado", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "El vehículo no existe en el inventario");
                });
        return mapearADTO(vehiculo);
    }

    // MÉTODO PRIVADO QUE USA WEBCLIENT
    private void validarModeloExiste(Long idModelo) {
        log.info("Verificando existencia del modelo ID {} en MS-Modelos", idModelo);
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/modelos/" + idModelo)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block(); // Espera la respuesta
        } catch (WebClientResponseException.NotFound ex) {
            log.error("El modelo ID {} no existe en MS-Modelos", idModelo);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede guardar: El modelo especificado no existe");
        } catch (Exception ex) {
            log.error("Error al conectar con MS-Modelos: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "El servicio de modelos no está disponible");
        }
    }

    private VehiculoResponseDTO mapearADTO(Vehiculo vehiculo) {
        VehiculoResponseDTO dto = new VehiculoResponseDTO();
        dto.setId(vehiculo.getId());
        dto.setVin(vehiculo.getVin());
        dto.setIdModelo(vehiculo.getIdModelo());
        dto.setCondicion(vehiculo.getCondicion());
        dto.setKilometraje(vehiculo.getKilometraje());
        dto.setPrecioVenta(vehiculo.getPrecioVenta());
        dto.setEstadoDisponibilidad(vehiculo.getEstadoDisponibilidad());
        return dto;
    }
}
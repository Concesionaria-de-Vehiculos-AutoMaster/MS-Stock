package com.automaster.msstock.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VehiculoRequestDTO {

    @NotBlank(message = "El VIN es obligatorio")
    private String vin;

    @NotNull(message = "El ID del modelo es obligatorio")
    private Long idModelo;

    @NotBlank(message = "La condición (Nuevo/Usado) es obligatoria")
    private String condicion;

    @Min(value = 0, message = "El kilometraje no puede ser negativo")
    private Integer kilometraje;

    @Positive(message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @NotBlank(message = "El estado es obligatorio")
    private String estadoDisponibilidad;
}
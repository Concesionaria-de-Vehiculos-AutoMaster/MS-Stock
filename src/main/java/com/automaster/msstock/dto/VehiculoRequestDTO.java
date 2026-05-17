package com.automaster.msstock.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VehiculoRequestDTO {

    @NotBlank(message = "El VIN es obligatorio")
    @Size(min = 17, max = 17, message = "El VIN debe tener exactamente 17 caracteres")
    private String vin;

    @NotNull(message = "El ID del modelo es obligatorio")
    private Long idModelo;

    @NotBlank(message = "La condición es obligatoria (Nuevo o Usado)")
    private String condicion;

    @NotNull(message = "El kilometraje es obligatorio")
    @Min(value = 0, message = "El kilometraje no puede ser negativo")
    private Integer kilometraje;

    @NotNull(message = "El precio de venta es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precioVenta;
}
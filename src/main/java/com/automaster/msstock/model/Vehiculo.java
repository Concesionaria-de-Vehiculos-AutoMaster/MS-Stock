package com.automaster.msstock.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.*;
        import lombok.Data;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @NotBlank(message = "El VIN (número de chasis) es obligatorio")
    @Column(unique = true)
    private String vin;

    @NotNull(message = "El ID del modelo es obligatorio")
    private Long idModelo;

    @NotBlank(message = "La condición es obligatoria")
    private String condicion;

    @Min(value = 0, message = "El kilometraje no puede ser negativo")
    private Integer kilometraje;

    @Positive(message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @NotBlank(message = "El estado de disponibilidad es obligatorio")
    private String estadoDisponibilidad;
}
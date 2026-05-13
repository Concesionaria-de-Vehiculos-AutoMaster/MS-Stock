package com.automaster.msstock.dto;

import lombok.Data;

@Data
public class VehiculoResponseDTO {
    private Long idVehiculo;
    private String vin;
    private Long idModelo;
    private String condicion;
    private Integer kilometraje;
    private Double precioVenta;
    private String estadoDisponibilidad;
}
package com.automaster.msstock.dto;

import lombok.Data;

@Data
public class VehiculoResponseDTO {
    private Long id;
    private String vin;
    private Long idModelo;
    private String condicion;
    private Integer kilometraje;
    private Double precioVenta;
    private String estadoDisponibilidad;
}
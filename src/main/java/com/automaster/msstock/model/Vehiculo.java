package com.automaster.msstock.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehiculos")
@Data
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 17)
    private String vin;

    @Column(name = "id_modelo", nullable = false)
    private Long idModelo; // No hay @ManyToOne aquí porque pertenece a otro microservicio

    @Column(nullable = false)
    private String condicion; // "Nuevo" o "Usado"

    @Column(nullable = false)
    private Integer kilometraje;

    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;

    @Column(name = "estado_disponibilidad", nullable = false)
    private String estadoDisponibilidad; // "Disponible", "Reservado", "Vendido"
}
package com.automaster.msstock.config;

import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (vehiculoRepository.count() == 0) {
            Vehiculo v1 = new Vehiculo();
            v1.setVin("1ABC23456789DEF");
            v1.setIdModelo(1L);
            v1.setCondicion("Nuevo");
            v1.setKilometraje(0);
            v1.setPrecioVenta(15000000.0);
            v1.setEstadoDisponibilidad("Disponible");

            vehiculoRepository.save(v1);
            System.out.println("Datos iniciales de MS-Stock cargados correctamente.");
        }
    }
}
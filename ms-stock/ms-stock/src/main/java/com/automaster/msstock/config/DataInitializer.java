package com.automaster.msstock.config;

import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
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

            Vehiculo v2 = new Vehiculo();
            v2.setVin("2ABC238329DEF");
            v2.setIdModelo(2L);
            v2.setCondicion("usado");
            v2.setKilometraje(40000);
            v2.setPrecioVenta(10000000.0);
            v2.setEstadoDisponibilidad("Disponible");

            vehiculoRepository.saveAll(Arrays.asList(v1,v2));
            log.info("Datos iniciales de MS-Stock cargados correctamente.");
        } else {
            log.info("La base de datos ya tiene ese Vehiculo");
        }
    }
}
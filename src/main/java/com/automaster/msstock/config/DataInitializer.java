package com.automaster.msstock.config;

import com.automaster.msstock.model.Vehiculo;
import com.automaster.msstock.repository.VehiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Verificando si existen vehículos en el inventario de la base de datos...");

        if (vehiculoRepository.count() == 0) {
            log.info("El inventario está vacío. Procediendo a cargar un vehículo de prueba...");

            Vehiculo vehiculo1 = new Vehiculo();
            vehiculo1.setVin("1HGCM82633A000001"); // Exactamente 17 caracteres
            vehiculo1.setIdModelo(1L); // Asumimos que vas a crear el modelo ID 1 en MS-Modelos
            vehiculo1.setCondicion("Nuevo");
            vehiculo1.setKilometraje(0);
            vehiculo1.setPrecioVenta(12000000.0); // 12 millones
            vehiculo1.setEstadoDisponibilidad("Disponible");

            vehiculoRepository.save(vehiculo1);

            log.info("Vehículo de prueba cargado exitosamente. Ideal para probar MS-Financiamiento.");
        } else {
            log.info("El inventario ya contiene datos. Omitiendo la carga inicial.");
        }
    }
}
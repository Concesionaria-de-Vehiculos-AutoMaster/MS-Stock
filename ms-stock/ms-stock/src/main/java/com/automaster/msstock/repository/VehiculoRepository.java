package com.automaster.msstock.repository;

import com.automaster.msstock.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findByCondicionIgnoreCase(String condicion);
    List<Vehiculo> findByPrecioVentaLessThan(Double precioMax);
    List<Vehiculo> findByEstadoDisponibilidad(String estado);


    @Query("SELECT v FROM Vehiculo v WHERE v.idModelo = :idModelo")
    List<Vehiculo> findByModeloId(@Param("idModelo") Long idModelo);

    @Query("SELECT v FROM Vehiculo v WHERE v.precioVenta <= :precioMax ORDER BY v.precioVenta DESC")
    List<Vehiculo> findVehiculosBajoPresupuesto(@Param("precioMax") Double precioMax);


    @Query(
            value = "SELECT * FROM vehiculos WHERE vin LIKE CONCAT('%', :vinParcial, '%')",
            nativeQuery = true
    )
    List<Vehiculo> buscarPorVinParcialNativo(@Param("vinParcial") String vinParcial);
}

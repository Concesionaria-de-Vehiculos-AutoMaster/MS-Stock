package com.automaster.msstock.repository;

import com.automaster.msstock.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
      // -------------------------------------------------------------------
    // --- TIPO 1: QUERY METHODS -----------------------------------------
    // Spring analiza el nombre del método y genera SQL.
    // El atributo debe coincidir EXACTAMENTE con el campo
    // de la entidad Java (mayúsculas incluidas).
    // -------------------------------------------------------------------

    // -> SELECT * FROM vehiculos WHERE UPPER(condicion) = UPPER(?)
    List<Vehiculo> findByCondicionIgnoreCase(String condicion);

    // -> SELECT * FROM vehiculos WHERE precio_venta < ?
    List<Vehiculo> findByPrecioVentaLessThan(Double precioMax);

    // -> SELECT * FROM vehiculos WHERE estado_disponibilidad = ?
    List<Vehiculo> findByEstadoDisponibilidad(String estado);


    // -------------------------------------------------------------------
    // --- TIPO 2: @QUERY CON JPQL ---------------------------------------
    // Escrito sobre entidades Java, NO sobre tablas SQL.
    // "Vehiculo" = clase Java, "v.idModelo" = atributo.
    // Hibernate lo traduce al SQL correcto según el dialecto (MariaDB).
    // :param es parámetro nombrado, @Param lo enlaza.
    // -------------------------------------------------------------------

    @Query("SELECT v FROM Vehiculo v WHERE v.idModelo = :idModelo")
    List<Vehiculo> findByModeloId(@Param("idModelo") Long idModelo);

    // JPQL con ORDER BY
    @Query("SELECT v FROM Vehiculo v WHERE v.precioVenta <= :precioMax ORDER BY v.precioVenta DESC")
    List<Vehiculo> findVehiculosBajoPresupuesto(@Param("precioMax") Double precioMax);


    // -------------------------------------------------------------------
    // --- TIPO 3: SQL NATIVO --------------------------------------------
    // nativeQuery=true: Hibernate manda el SQL tal cual a MariaDB.
    // Usar para funciones específicas de base de datos (CONCAT...)
    // o consultas muy optimizadas que no deben ser reescritas.
    // -------------------------------------------------------------------

    @Query(
            value = "SELECT * FROM vehiculos WHERE vin LIKE CONCAT('%', :vinParcial, '%')",
            nativeQuery = true
    )
    List<Vehiculo> buscarPorVinParcialNativo(@Param("vinParcial") String vinParcial);
}

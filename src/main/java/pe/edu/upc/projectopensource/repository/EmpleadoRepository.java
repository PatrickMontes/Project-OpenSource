package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    @Query("""
        SELECT e
        FROM Empleado e
        WHERE (:dni IS NULL OR e.dni = :dni)
          AND (:nombres IS NULL OR LOWER(e.nombres) LIKE LOWER(CONCAT('%', :nombres, '%')))
          AND (:apellidos IS NULL OR LOWER(e.apellidos) LIKE LOWER(CONCAT('%', :apellidos, '%')))
          AND (:sexo IS NULL OR e.sexo = :sexo)
          AND (:estado IS NULL OR e.estado = :estado)
          AND (:asegurado IS NULL OR e.asegurado = :asegurado)
    """)
    List<Empleado> buscarEmpleados(
            @Param("dni") Integer dni,
            @Param("nombres") String nombres,
            @Param("apellidos") String apellidos,
            @Param("sexo") SexoType sexo,
            @Param("estado") EstadoType estado,
            @Param("asegurado") Boolean asegurado
    );
}

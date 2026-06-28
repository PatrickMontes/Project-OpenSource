package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Descuento;

import java.util.List;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    @Query("""
        SELECT d
        FROM Descuento d
        WHERE (:empleadoId IS NULL OR d.empleado.id = :empleadoId)
          AND (:semanaId IS NULL OR d.semana.id = :semanaId)
    """)
    List<Descuento> buscarDescuentos(
            @Param("empleadoId") Long empleadoId,
            @Param("semanaId") Long semanaId
    );
}

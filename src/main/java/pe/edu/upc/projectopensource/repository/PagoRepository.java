package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Pago;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    @Query("""
    SELECT p FROM Pago p
    WHERE (:empleadoId IS NULL OR p.empleado.id = :empleadoId)
      AND (:planillaId IS NULL OR p.planilla.id = :planillaId)
      AND (:semanaId IS NULL OR p.planilla.semana.id = :semanaId)
    """)
    List<Pago> findPagos(
            @Param("empleadoId") Long empleadoId,
            @Param("planillaId") Long planillaId,
            @Param("semanaId") Long semanaId
    );

    Optional<Pago> findFirstByEmpleadoIdOrderByIdDesc(Long empleadoId);

}

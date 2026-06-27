package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Asistencia;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsisteciaType;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("""
    SELECT a FROM Asistencia a
    WHERE (:empleadoId IS NULL OR a.empleado.id = :empleadoId)
    AND (:semanaId IS NULL OR a.semana.id = :semanaId)
    AND (:estado IS NULL OR a.estado = :estado)
    AND (
        :fechaInicio IS NULL
        OR :fechaFin IS NULL
        OR a.fecha BETWEEN :fechaInicio AND :fechaFin
    )
    """)
    List<Asistencia> findAsistencias( Long empleadoId, Long semanaId, EstadoAsisteciaType estado, LocalDate fechaInicio, LocalDate fechaFin);
}

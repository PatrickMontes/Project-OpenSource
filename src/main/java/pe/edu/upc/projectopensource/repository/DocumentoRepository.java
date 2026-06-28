package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    @Query("""
        SELECT d
        FROM Documento d
        WHERE (:empleadoId IS NULL OR d.empleado.id = :empleadoId)
          AND (:tipoDocumento IS NULL OR d.tipoDocumento = :tipoDocumento)
    """)
    List<Documento> buscarDocumentos(
            @Param("empleadoId") Long empleadoId,
            @Param("tipoDocumento") DocumentoType tipoDocumento
    );
}

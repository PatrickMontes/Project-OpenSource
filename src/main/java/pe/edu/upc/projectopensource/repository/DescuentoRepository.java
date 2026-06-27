package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
}

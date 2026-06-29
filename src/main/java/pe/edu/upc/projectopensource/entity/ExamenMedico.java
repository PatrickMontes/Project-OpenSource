package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Embeddable
public class ExamenMedico {
    @Column(name = "examen_fecha")
    private LocalDate fecha;

    @Column(name = "examen_monto_gastado")
    private BigDecimal montoGastado;

    @Column(name = "examen_alta")
    private Boolean alta;
}

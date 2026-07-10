package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Embeddable
public class ExamenMedico {
    @Column(name = "examen_fecha")
    @Past(message = "La fecha del examen debe ser una fecha pasada")
    private LocalDate fecha;

    @Column(name = "examen_monto_gastado")
    private BigDecimal montoGastado;

    @Column(name = "examen_alta")
    private Boolean alta;
}

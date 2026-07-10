package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenMedicoDTO {
    @Past(message = "La fecha del examen debe ser una fecha pasada")
    private LocalDate fecha;

    private BigDecimal montoGastado;

    private Boolean alta;
}

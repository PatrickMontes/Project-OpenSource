package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoSemanaType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemanaDTO {
    private Long id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El número de semana es obligatorio")
    @Min(value = 1, message = "El número de semana no puede ser menor a 1")
    private Integer numeroSemana;

    @NotNull(message = "El turno es obligatorio")
    private Long turnoId;

    @NotNull(message = "El estado de la semana es obligatorio")
    private EstadoSemanaType estado;
}

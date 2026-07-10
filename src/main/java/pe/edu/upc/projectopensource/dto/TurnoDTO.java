package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.TipoTurnoType;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {
    private Long id;

    @NotNull(message = "El tipo de turno es obligatorio")
    private TipoTurnoType tipo;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;
}

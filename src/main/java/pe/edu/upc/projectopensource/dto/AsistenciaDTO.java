package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsistenciaType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaDTO {
    private Long id;

    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "La semana es obligatoria")
    private Long semanaId;

    @PastOrPresent(message = "La fecha de asistencia no puede ser en el futuro")
    private LocalDate fecha;

    private EstadoAsistenciaType estado;

    @Min(value = 0, message = "Los minutos extras no pueden ser un valor negativo")
    private Integer minutosExtras;

    @Min(value = 0, message = "Los minutos de atraso no pueden ser un valor negativo")
    private Integer minutosAtrasadas;

    private LocalDateTime fechaModificacion;

    private String observaciones;
}

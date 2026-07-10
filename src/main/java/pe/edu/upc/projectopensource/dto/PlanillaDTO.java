package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaDTO {
    private Long id;

    @NotNull(message = "La semana es obligatoria")
    private Long semanaId;

    @NotNull(message = "El total gastado es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total gastado no puede ser negativo")
    private BigDecimal totalGastado;
}

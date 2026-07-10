package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoDTO {
    private Long id;

    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "La semana es obligatoria")
    private Long semanaId;

    @NotNull(message = "El monto del descuento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto del descuento debe ser mayor a 0")
    private BigDecimal monto;

    @NotBlank(message = "La razón del descuento es obligatoria")
    private String razon;
}

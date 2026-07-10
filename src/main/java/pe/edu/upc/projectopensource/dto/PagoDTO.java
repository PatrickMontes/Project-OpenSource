package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;

    private Long planillaId;

    private Long empleadoId;

    @NotNull(message = "El pago bruto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El pago bruto no puede ser negativo")
    private BigDecimal pagoBruto;

    @Min(value = 0, message = "Los minutos extras totales no pueden ser negativos")
    private Integer minutosExtrasTotales;

    @NotNull(message = "El pago neto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El pago neto no puede ser negativo")
    private BigDecimal pagoNeto;
}

package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.CargoType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {
    private Long id;

    @NotNull(message = "El nombre del cargo es obligatorio")
    private CargoType nombre;

    @NotNull(message = "El salario semanal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El salario semanal debe ser mayor a 0")
    private BigDecimal salarioSemanal;
}

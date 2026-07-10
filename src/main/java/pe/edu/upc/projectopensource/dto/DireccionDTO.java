package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
    @NotBlank(message = "El departamento es obligatorio")
    private String departamento;

    @NotBlank(message = "La provincia es obligatoria")
    private String provincia;

    @NotBlank(message = "El distrito es obligatorio")
    private String distrito;

    @NotBlank(message = "El domicilio es obligatorio")
    private String domicilio;
}

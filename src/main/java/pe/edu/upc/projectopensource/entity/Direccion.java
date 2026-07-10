package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Embeddable
public class Direccion {
    @NotBlank(message = "El departamento es obligatorio")
    private String departamento;

    @NotBlank(message = "La provincia es obligatoria")
    private String provincia;

    @NotBlank(message = "El distrito es obligatorio")
    private String distrito;

    @NotBlank(message = "El domicilio es obligatorio")
    private String domicilio;
}

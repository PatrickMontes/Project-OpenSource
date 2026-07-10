package pe.edu.upc.projectopensource.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private Long id;

    @NotNull(message = "El DNI es obligatorio")
    @Min(value = 10000000, message = "El DNI debe tener 8 dígitos")
    @Max(value = 99999999, message = "El DNI debe tener 8 dígitos")
    private Integer dni;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private Integer edad;

    @NotNull(message = "El sexo es obligatorio")
    private SexoType sexo;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String correo;

    @NotNull(message = "El celular es obligatorio")
    @Min(value = 100000000, message = "El celular debe tener 9 dígitos")
    @Max(value = 999999999, message = "El celular debe tener 9 dígitos")
    private Integer celular;

    @NotNull(message = "El asegurado es obligatorio")
    private Boolean asegurado;

    @NotNull(message = "El cargo es obligatorio")
    private Long cargoId;

    @NotNull(message = "El estado es obligatorio")
    private EstadoType estado;

    @Valid
    private DireccionDTO direccion;

    @Valid
    private ExamenMedicoDTO examenMedico;
}

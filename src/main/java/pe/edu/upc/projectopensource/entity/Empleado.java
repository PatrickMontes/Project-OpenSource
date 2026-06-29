package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;


import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private Integer dni;

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El sexo es obligatorio")
    private SexoType sexo;

    @Column(name = "fecha_nacimiento")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String correo;

    @NotNull(message = "El celular es obligatorio")
    @Size(min = 9, max = 9, message = "El celular debe tener 9 dígitos")
    private Integer celular;

    @NotNull(message = "El asegurado es obligatorio")
    private Boolean asegurado;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    @NotNull(message = "El cargo es obligatorio")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado es obligatorio")
    private EstadoType estado;

    @Embedded
    private Direccion direccion;

    @Embedded
    private ExamenMedico examenMedico;
}
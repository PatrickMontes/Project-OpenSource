package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
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

    private Integer dni;

    private String nombres;

    private String apellidos;

    private Integer edad;

    @Enumerated(EnumType.STRING)
    private SexoType sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String correo;

    private Integer celular;

    private Boolean asegurado;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private EstadoType estado;
}
package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoSemanaType;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "semanas")
public class Semana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @Column(name = "numero_semana")
    @NotNull(message = "El número de semana es obligatorio")
    @Min(value = 1, message = "El número de semana no puede ser menor a 1")
    private Integer numeroSemana;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    @NotNull(message = "El turno es obligatorio")
    private Turno turno;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado de la semana es obligatorio")
    private EstadoSemanaType estado;

    @Transient
    private Long turnoId;

}
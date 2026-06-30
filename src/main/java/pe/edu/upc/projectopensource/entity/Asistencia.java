package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsistenciaType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    @NotNull(message = "El empleado es obligatorio")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "semana_id")
    @NotNull(message = "La semana es obligatoria")
    private Semana semana;

    @PastOrPresent(message = "La fecha de asistencia no puede ser en el futuro")
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoAsistenciaType estado;

    @Column(name = "minutos_extras")
    @Min(value = 0, message = "Los minutos extras no pueden ser un valor negativo")
    private Integer minutosExtras;

    @Column(name = "minutos_atrasadas")
    @Min(value = 0, message = "Los minutos de atraso no pueden ser un valor negativo")
    private Integer minutosAtrasadas;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    private String observaciones;
}

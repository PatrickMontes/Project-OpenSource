package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsisteciaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "semana_id")
    private Semana semana;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoAsisteciaType estado;

    @Column(name = "minutos_extras")
    private Integer minutosExtras;

    @Column(name = "minutos_atrasadas")
    private Integer minutosAtrasadas;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    private String observaciones;
}

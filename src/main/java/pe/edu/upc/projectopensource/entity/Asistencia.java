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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "semana_id")
    private Semana semana;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoAsisteciaType estado;

    private Integer minutosExtras;

    private Integer minutosAtrasadas;

    private LocalDateTime fechaModificacion;

    private String observaciones;
}

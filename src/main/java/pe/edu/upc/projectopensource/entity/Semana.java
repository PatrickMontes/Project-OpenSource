package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Integer numeroSemana;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;

    @Enumerated(EnumType.STRING)
    private EstadoSemanaType estado;
}
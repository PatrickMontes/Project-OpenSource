package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.TipoTurnoType;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoTurnoType tipo;

    private LocalTime horaInicio;

    private LocalTime horaFin;
}
package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planillas")
public class Planilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "semana_id")
    @NotNull(message = "La semana es obligatoria")
    private Semana semana;

    @Column(name = "total_gastado")
    @NotNull(message = "El total gastado es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total gastado no puede ser negativo")
    private BigDecimal totalGastado;
}
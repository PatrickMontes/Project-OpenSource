package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planilla_id")
    private Planilla planilla;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @Column(name = "pago_bruto")
    @NotNull(message = "El pago bruto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El pago bruto no puede ser negativo")
    private BigDecimal pagoBruto;

    @Column(name = "minutos_extras_totales")
    @Min(value = 0, message = "Los minutos extras totales no pueden ser negativos")
    private Integer minutosExtrasTotales = 0;

    @Column(name = "pago_neto")
    @NotNull(message = "El pago neto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El pago neto no puede ser negativo")
    private BigDecimal pagoNeto;
}
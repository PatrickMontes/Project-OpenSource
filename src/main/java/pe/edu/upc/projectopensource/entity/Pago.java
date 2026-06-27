package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
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
    private BigDecimal pagoBruto;

    @Column(name = "minutos_extras_totales")
    private Integer minutosExtrasTotales;

    @Column(name = "pago_neto")
    private BigDecimal pagoNeto;
}
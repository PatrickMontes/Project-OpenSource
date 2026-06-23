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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "planilla_id")
    private Planilla planilla;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private BigDecimal pagoBruto;

    private Integer minutosExtrasTotales;

    private BigDecimal pagoNeto;
}
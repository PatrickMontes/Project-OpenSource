package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "descuentos")
public class Descuento {

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

    @NotNull(message = "El monto del descuento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto del descuento debe ser mayor a 0")
    private BigDecimal monto;

    @NotBlank(message = "La razón del descuento es obligatoria")
    private String razon;
}
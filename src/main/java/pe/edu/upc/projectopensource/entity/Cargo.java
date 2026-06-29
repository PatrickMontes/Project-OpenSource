package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.CargoType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El nombre del cargo es obligatorio")
    private CargoType nombre;

    @Column(name = "salario_semanal")
    @NotNull(message = "El salario semanal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El salario semanal debe ser mayor a 0")
    private BigDecimal salarioSemanal;
}

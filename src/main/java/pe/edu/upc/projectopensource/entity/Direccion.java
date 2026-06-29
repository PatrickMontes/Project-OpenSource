package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Embeddable
public class Direccion {
    private String departamento;
    private String provincia;
    private String distrito;
    private String domicilio;
}

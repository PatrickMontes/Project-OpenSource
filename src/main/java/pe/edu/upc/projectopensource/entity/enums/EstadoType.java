package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EstadoType {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String descripcion;

    EstadoType(String descripcion) {
        this.descripcion = descripcion;
    }
}

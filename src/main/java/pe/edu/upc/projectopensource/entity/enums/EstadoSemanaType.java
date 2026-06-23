package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EstadoSemanaType {
    ACTIVA("Activa"),
    COMPLETADA("Completada"),
    PENDIENTE("Pendiente"),
    CANCELADA("Cancelada");

    private final String descripcion;

    EstadoSemanaType(String descripcion) {
        this.descripcion = descripcion;
    }
}

package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum SexoType {
    MASCULINO("Masculino"),
    FEMENINO("Femenino");

    private final String descripcion;

    SexoType(String descripcion) {
        this.descripcion = descripcion;
    }
}

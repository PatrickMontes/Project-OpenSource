package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum TipoTurnoType {
    MATUTINO("Matutino"),
    TARDE("Tarde"),
    NOCTURNO("Nocturno"),
    COMPLETO("Completo");

    private final String descripcion;

    TipoTurnoType(String descripcion) {
        this.descripcion = descripcion;
    }
}

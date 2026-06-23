package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EstadoAsisteciaType {
    PRESENTE("Presente"),
    TARDE("Tarde"),
    AUSENTE("Ausente"),
    JUSTIFICADO("Justificado");

    private final String descripcion;

    EstadoAsisteciaType(String descripcion) {
        this.descripcion = descripcion;
    }

}

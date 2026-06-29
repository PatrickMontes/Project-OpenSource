package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EstadoAsistenciaType {
    PRESENTE("Presente"),
    TARDE("Tarde"),
    AUSENTE("Ausente"),
    JUSTIFICADO("Justificado");

    private final String descripcion;

    EstadoAsistenciaType(String descripcion) {
        this.descripcion = descripcion;
    }

}

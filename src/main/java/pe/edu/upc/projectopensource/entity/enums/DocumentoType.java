package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum DocumentoType {
    MEDICO("Médico"),
    JUDICIAL("Judicial");

    private final String descripcion;

    DocumentoType(String descripcion) {
        this.descripcion = descripcion;
    }
}

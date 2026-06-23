package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum CargoType {
    OPERARIO_EMPASTADOR("Operario Empastador"),
    OPERARIO_PINTOR("Operario Pintor"),
    OPERARIO_CAPATAZ("Operario Capataz"),
    OPERARIO_OFICIAL("Operario Oficial"),
    SUPERVISOR("Supervisor"),
    AYUDANTE("Ayudante"),
    LIMPIEZA("Limpieza");

    private final String descripcion;

    CargoType(String descripcion) {
        this.descripcion = descripcion;
    }

}
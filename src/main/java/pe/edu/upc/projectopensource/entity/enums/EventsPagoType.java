package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EventsPagoType {
    PAGO_CREADO("pago.creado");

    private final String descripcion;

    EventsPagoType(String descripcion) {
        this.descripcion = descripcion;
    }
}

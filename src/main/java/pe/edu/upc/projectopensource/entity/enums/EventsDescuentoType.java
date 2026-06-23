package pe.edu.upc.projectopensource.entity.enums;

import lombok.Getter;

@Getter
public enum EventsDescuentoType {
    DESCUENTO_CREADO("descuento.creado");

    private final String descripcion;

    EventsDescuentoType(String descripcion) {
        this.descripcion = descripcion;
    }

}

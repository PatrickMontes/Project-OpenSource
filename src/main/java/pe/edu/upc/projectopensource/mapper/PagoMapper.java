package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.PagoDTO;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Pago;
import pe.edu.upc.projectopensource.entity.Planilla;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(target = "planillaId", source = "planilla.id")
    @Mapping(target = "empleadoId", source = "empleado.id")
    PagoDTO toDto(Pago pago);

    @Mapping(target = "planilla", source = "planillaId", qualifiedByName = "planillaFromId")
    @Mapping(target = "empleado", source = "empleadoId", qualifiedByName = "empleadoFromId")
    Pago toEntity(PagoDTO pagoDTO);

    @Named("planillaFromId")
    default Planilla planillaFromId(Long id) {
        if (id == null) return null;
        Planilla planilla = new Planilla();
        planilla.setId(id);
        return planilla;
    }

    @Named("empleadoFromId")
    default Empleado empleadoFromId(Long id) {
        if (id == null) return null;
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
    }
}

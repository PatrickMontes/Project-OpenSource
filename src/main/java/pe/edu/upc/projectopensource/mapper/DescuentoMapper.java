package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.DescuentoDTO;
import pe.edu.upc.projectopensource.entity.Descuento;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Semana;

@Mapper(componentModel = "spring")
public interface DescuentoMapper {

    @Mapping(target = "empleadoId", source = "empleado.id")
    @Mapping(target = "semanaId", source = "semana.id")
    DescuentoDTO toDto(Descuento descuento);

    @Mapping(target = "empleado", source = "empleadoId", qualifiedByName = "empleadoFromId")
    @Mapping(target = "semana", source = "semanaId", qualifiedByName = "semanaFromId")
    Descuento toEntity(DescuentoDTO descuentoDTO);

    @Named("empleadoFromId")
    default Empleado empleadoFromId(Long id) {
        if (id == null) return null;
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
    }

    @Named("semanaFromId")
    default Semana semanaFromId(Long id) {
        if (id == null) return null;
        Semana semana = new Semana();
        semana.setId(id);
        return semana;
    }
}

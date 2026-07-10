package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.AsistenciaDTO;
import pe.edu.upc.projectopensource.entity.Asistencia;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Semana;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {

    @Mapping(target = "empleadoId", source = "empleado.id")
    @Mapping(target = "semanaId", source = "semana.id")
    AsistenciaDTO toDto(Asistencia asistencia);

    @Mapping(target = "empleado", source = "empleadoId", qualifiedByName = "empleadoFromId")
    @Mapping(target = "semana", source = "semanaId", qualifiedByName = "semanaFromId")
    Asistencia toEntity(AsistenciaDTO asistenciaDTO);

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

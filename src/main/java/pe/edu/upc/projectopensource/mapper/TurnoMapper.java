package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import pe.edu.upc.projectopensource.dto.TurnoDTO;
import pe.edu.upc.projectopensource.entity.Turno;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    TurnoDTO toDto(Turno turno);
    Turno toEntity(TurnoDTO turnoDTO);
}

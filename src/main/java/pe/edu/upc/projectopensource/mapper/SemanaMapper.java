package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.SemanaDTO;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.entity.Turno;

@Mapper(componentModel = "spring")
public interface SemanaMapper {

    @Mapping(target = "turnoId", source = "turno.id")
    SemanaDTO toDto(Semana semana);

    @Mapping(target = "turno", source = "turnoId", qualifiedByName = "turnoFromId")
    Semana toEntity(SemanaDTO semanaDTO);

    @Named("turnoFromId")
    default Turno turnoFromId(Long id) {
        if (id == null) return null;
        Turno turno = new Turno();
        turno.setId(id);
        return turno;
    }
}

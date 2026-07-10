package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.PlanillaDTO;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.entity.Semana;

@Mapper(componentModel = "spring")
public interface PlanillaMapper {

    @Mapping(target = "semanaId", source = "semana.id")
    PlanillaDTO toDto(Planilla planilla);

    @Mapping(target = "semana", source = "semanaId", qualifiedByName = "semanaFromId")
    Planilla toEntity(PlanillaDTO planillaDTO);

    @Named("semanaFromId")
    default Semana semanaFromId(Long id) {
        if (id == null) return null;
        Semana semana = new Semana();
        semana.setId(id);
        return semana;
    }
}

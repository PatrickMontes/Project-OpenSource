package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import pe.edu.upc.projectopensource.dto.ExamenMedicoDTO;
import pe.edu.upc.projectopensource.entity.ExamenMedico;

@Mapper(componentModel = "spring")
public interface ExamenMedicoMapper {
    ExamenMedicoDTO toDto(ExamenMedico examenMedico);
    ExamenMedico toEntity(ExamenMedicoDTO examenMedicoDTO);
}

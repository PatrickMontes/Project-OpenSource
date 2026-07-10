package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import pe.edu.upc.projectopensource.dto.DireccionDTO;
import pe.edu.upc.projectopensource.entity.Direccion;

@Mapper(componentModel = "spring")
public interface DireccionMapper {
    DireccionDTO toDto(Direccion direccion);
    Direccion toEntity(DireccionDTO direccionDTO);
}

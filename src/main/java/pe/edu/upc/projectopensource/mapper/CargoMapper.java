package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import pe.edu.upc.projectopensource.dto.CargoDTO;
import pe.edu.upc.projectopensource.entity.Cargo;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    CargoDTO toDto(Cargo cargo);
    Cargo toEntity(CargoDTO cargoDTO);
}

package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.EmpleadoDTO;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.entity.Empleado;

@Mapper(componentModel = "spring", uses = {DireccionMapper.class, ExamenMedicoMapper.class})
public interface EmpleadoMapper {

    @Mapping(target = "cargoId", source = "cargo.id")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "examenMedico", source = "examenMedico")
    EmpleadoDTO toDto(Empleado empleado);

    @Mapping(target = "cargo", source = "cargoId", qualifiedByName = "cargoFromId")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "examenMedico", source = "examenMedico")
    Empleado toEntity(EmpleadoDTO empleadoDTO);

    @Named("cargoFromId")
    default Cargo cargoFromId(Long id) {
        if (id == null) return null;
        Cargo cargo = new Cargo();
        cargo.setId(id);
        return cargo;
    }
}

package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.edu.upc.projectopensource.dto.DocumentoDTO;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.Empleado;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    @Mapping(target = "empleadoId", source = "empleado.id")
    DocumentoDTO toDto(Documento documento);

    @Mapping(target = "empleado", source = "empleadoId", qualifiedByName = "empleadoFromId")
    Documento toEntity(DocumentoDTO documentoDTO);

    @Named("empleadoFromId")
    default Empleado empleadoFromId(Long id) {
        if (id == null) return null;
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
    }
}

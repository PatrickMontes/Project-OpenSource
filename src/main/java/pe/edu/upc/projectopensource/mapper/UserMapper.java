package pe.edu.upc.projectopensource.mapper;

import org.mapstruct.Mapper;
import pe.edu.upc.projectopensource.dto.UserDTO;
import pe.edu.upc.projectopensource.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}

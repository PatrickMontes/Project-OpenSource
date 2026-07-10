package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.UserDTO;
import pe.edu.upc.projectopensource.entity.User;
import pe.edu.upc.projectopensource.mapper.UserMapper;
import pe.edu.upc.projectopensource.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO crearUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        user.setRole("USER");
        return userMapper.toDto(userRepository.save(user));
    }

    public List<UserDTO> obtenerUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDTO obtenerUser(Long id){
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));
    }

    public UserDTO actualizarUser(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User no encontrado"));

        user.setNombres(userDTO.getNombres());
        user.setApellidos(userDTO.getApellidos());
        user.setEmail(userDTO.getEmail());
        user.setIsActive(userDTO.getIsActive());
        user.setRole(userDTO.getRole());

        return userMapper.toDto(userRepository.save(user));
    }

    public void eliminarUser(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User no encontrado"));

        userRepository.delete(user);
    }
}

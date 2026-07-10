package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.AuthResponse;
import pe.edu.upc.projectopensource.dto.LoginRequest;
import pe.edu.upc.projectopensource.dto.RegisterRequest;
import pe.edu.upc.projectopensource.dto.UserDTO;
import pe.edu.upc.projectopensource.service.AuthService;
import pe.edu.upc.projectopensource.service.UserService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Autenticación y gestión de usuarios")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Response<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse data = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<AuthResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Inicio de sesión exitoso")
                        .data(data)
                        .build());
    }

    @PostMapping("/register")
    public ResponseEntity<Response<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse data = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<AuthResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Usuario registrado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/usuarios/lista")
    public ResponseEntity<Response<List<UserDTO>>> obtenerUsers() {
        List<UserDTO> data = userService.obtenerUsers();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<UserDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de usuarios")
                        .data(data)
                        .build());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Response<UserDTO>> obtenerUser(@PathVariable Long id) {
        UserDTO data = userService.obtenerUser(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<UserDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Usuario encontrado")
                        .data(data)
                        .build());
    }

    @PutMapping("/usuarios/actualizar/{id}")
    public ResponseEntity<Response<UserDTO>> actualizarUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO
    ) {
        UserDTO data = userService.actualizarUser(id, userDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<UserDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Usuario actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarUser(@PathVariable Long id) {
        userService.eliminarUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Usuario eliminado exitosamente")
                        .build());
    }
}

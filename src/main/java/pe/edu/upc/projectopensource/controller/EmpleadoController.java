package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.DireccionDTO;
import pe.edu.upc.projectopensource.dto.EmpleadoDTO;
import pe.edu.upc.projectopensource.dto.ExamenMedicoDTO;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;
import pe.edu.upc.projectopensource.service.EmpleadoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "Gestión de empleados: datos personales, dirección y examen médico")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping("/crear")
    public ResponseEntity<Response<EmpleadoDTO>> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO data = empleadoService.crearEmpleado(empleadoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<EmpleadoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Empleado creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<EmpleadoDTO>>> obtenerEmpleados() {
        List<EmpleadoDTO> data = empleadoService.obtenerEmpleados();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<EmpleadoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de empleados")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<EmpleadoDTO>> obtenerEmpleado(@PathVariable Long id) {
        EmpleadoDTO data = empleadoService.obtenerEmpleado(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<EmpleadoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Empleado encontrado")
                        .data(data)
                        .build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Response<List<EmpleadoDTO>>> buscarEmpleados(
            @RequestParam(required = false) Integer dni,
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) SexoType sexo,
            @RequestParam(required = false) EstadoType estado,
            @RequestParam(required = false) Boolean asegurado
    ) {
        List<EmpleadoDTO> data = empleadoService.buscarEmpleados(dni, nombres, apellidos, sexo, estado, asegurado);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<EmpleadoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Resultados de búsqueda")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<EmpleadoDTO>> actualizarEmpleado(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoDTO empleadoDTO
    ) {
        EmpleadoDTO data = empleadoService.actualizarEmpleado(id, empleadoDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<EmpleadoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Empleado actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Empleado eliminado exitosamente")
                        .build());
    }

    @PutMapping("/{id}/direccion")
    public ResponseEntity<Response<EmpleadoDTO>> actualizarDireccion(
            @PathVariable Long id,
            @Valid @RequestBody DireccionDTO direccionDTO
    ) {
        EmpleadoDTO data = empleadoService.actualizarDireccionEmpleado(id, direccionDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<EmpleadoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Dirección actualizada exitosamente")
                        .data(data)
                        .build());
    }

    @PutMapping("/{id}/examen-medico")
    public ResponseEntity<Response<EmpleadoDTO>> actualizarExamenMedico(
            @PathVariable Long id,
            @Valid @RequestBody ExamenMedicoDTO examenMedicoDTO
    ) {
        EmpleadoDTO data = empleadoService.actualizarExamenMedicoEmpleado(id, examenMedicoDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<EmpleadoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Examen médico actualizado exitosamente")
                        .data(data)
                        .build());
    }
}

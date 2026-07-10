package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.AsistenciaDTO;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsistenciaType;
import pe.edu.upc.projectopensource.service.AsistenciaService;
import pe.edu.upc.projectopensource.utils.Response;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@Tag(name = "Asistencias", description = "Registro y consulta de asistencias de los empleados")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @PostMapping("/crear")
    public ResponseEntity<Response<AsistenciaDTO>> crearAsistencia(@Valid @RequestBody AsistenciaDTO asistenciaDTO) {
        AsistenciaDTO data = asistenciaService.crearAsistencia(asistenciaDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<AsistenciaDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Asistencia creada exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<AsistenciaDTO>>> obtenerAsistencias() {
        List<AsistenciaDTO> data = asistenciaService.obtenerAsistencias();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<AsistenciaDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de asistencias")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AsistenciaDTO>> obtenerAsistencia(@PathVariable Long id) {
        AsistenciaDTO data = asistenciaService.obtenerAsistencia(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<AsistenciaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Asistencia encontrada")
                        .data(data)
                        .build());
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<Response<List<AsistenciaDTO>>> obtenerAsistenciasPorEmpleado(@PathVariable Long empleadoId) {
        List<AsistenciaDTO> data = asistenciaService.buscarAsistencias(empleadoId, null, null, null, null);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<AsistenciaDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Asistencias del empleado")
                        .data(data)
                        .build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Response<List<AsistenciaDTO>>> buscarAsistencias(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long semanaId,
            @RequestParam(required = false) EstadoAsistenciaType estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        List<AsistenciaDTO> data = asistenciaService.buscarAsistencias(empleadoId, semanaId, estado, fechaInicio, fechaFin);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<AsistenciaDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Resultados de búsqueda")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<AsistenciaDTO>> actualizarAsistencia(
            @PathVariable Long id,
            @Valid @RequestBody AsistenciaDTO asistenciaDTO
    ) {
        AsistenciaDTO data = asistenciaService.actualizarAsistencia(id, asistenciaDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<AsistenciaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Asistencia actualizada exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarAsistencia(@PathVariable Long id) {
        asistenciaService.eliminarAsistencia(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Asistencia eliminada exitosamente")
                        .build());
    }
}

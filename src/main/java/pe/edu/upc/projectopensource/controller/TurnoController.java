package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.TurnoDTO;
import pe.edu.upc.projectopensource.service.TurnoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@Tag(name = "Turnos", description = "Gestión de turnos de trabajo")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping("/crear")
    public ResponseEntity<Response<TurnoDTO>> crearTurno(@Valid @RequestBody TurnoDTO turnoDTO) {
        TurnoDTO data = turnoService.crearTurno(turnoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<TurnoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Turno creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<TurnoDTO>>> obtenerTurnos() {
        List<TurnoDTO> data = turnoService.obtenerTurnos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<TurnoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de turnos")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TurnoDTO>> obtenerTurno(@PathVariable Long id) {
        TurnoDTO data = turnoService.obtenerTurno(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<TurnoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Turno encontrado")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<TurnoDTO>> actualizarTurno(
            @PathVariable Long id,
            @Valid @RequestBody TurnoDTO turnoDTO
    ) {
        TurnoDTO data = turnoService.actualizarTurno(id, turnoDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<TurnoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Turno actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Turno eliminado exitosamente")
                        .build());
    }
}

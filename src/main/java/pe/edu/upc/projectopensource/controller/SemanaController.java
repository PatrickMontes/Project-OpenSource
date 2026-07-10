package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.SemanaDTO;
import pe.edu.upc.projectopensource.service.SemanaService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/semanas")
@Tag(name = "Semanas", description = "Gestión de semanas laborales y su estado")
@RequiredArgsConstructor
public class SemanaController {

    private final SemanaService semanaService;

    @PostMapping("/crear")
    public ResponseEntity<Response<SemanaDTO>> crearSemana(@Valid @RequestBody SemanaDTO semanaDTO) {
        SemanaDTO data = semanaService.crearSemana(semanaDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<SemanaDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Semana creada exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<SemanaDTO>>> obtenerSemanas() {
        List<SemanaDTO> data = semanaService.obtenerSemanas();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<SemanaDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de semanas")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<SemanaDTO>> obtenerSemana(@PathVariable Long id) {
        SemanaDTO data = semanaService.obtenerSemana(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<SemanaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Semana encontrada")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<SemanaDTO>> actualizarSemana(
            @PathVariable Long id,
            @Valid @RequestBody SemanaDTO semanaDTO
    ) {
        SemanaDTO data = semanaService.actualizarSemana(id, semanaDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<SemanaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Semana actualizada exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarSemana(@PathVariable Long id) {
        semanaService.eliminarSemana(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Semana eliminada exitosamente")
                        .build());
    }
}

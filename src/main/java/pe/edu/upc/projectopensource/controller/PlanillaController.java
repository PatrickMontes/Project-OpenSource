package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.PlanillaDTO;
import pe.edu.upc.projectopensource.service.PlanillaService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/planillas")
@Tag(name = "Planillas", description = "Generación de planillas semanales y cálculo del total gastado")
@RequiredArgsConstructor
public class PlanillaController {

    private final PlanillaService planillaService;

    @PostMapping("/generar/{semanaId}")
    public ResponseEntity<Response<PlanillaDTO>> crearPlanilla(@PathVariable Long semanaId) {
        PlanillaDTO data = planillaService.crearPlanilla(semanaId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<PlanillaDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Planilla generada exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<PlanillaDTO>>> obtenerPlanillas() {
        List<PlanillaDTO> data = planillaService.obtenerPlanillas();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<PlanillaDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de planillas")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<PlanillaDTO>> obtenerPlanilla(@PathVariable Long id) {
        PlanillaDTO data = planillaService.obtenerPlanilla(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<PlanillaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Planilla encontrada")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<PlanillaDTO>> actualizarPlanilla(
            @PathVariable Long id,
            @RequestParam(required = false) Long semanaId
    ) {
        PlanillaDTO data = planillaService.actualizarPlanilla(id, semanaId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<PlanillaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Planilla actualizada exitosamente")
                        .data(data)
                        .build());
    }

    @PutMapping("/{id}/recalcular-total")
    public ResponseEntity<Response<PlanillaDTO>> actualizarTotalGastadoPlanilla(@PathVariable Long id) {
        PlanillaDTO data = planillaService.actualizarTotalGastadoPlanilla(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<PlanillaDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Total gastado recalculado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarPlanilla(@PathVariable Long id) {
        planillaService.eliminarPlanilla(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Planilla eliminada exitosamente")
                        .build());
    }
}

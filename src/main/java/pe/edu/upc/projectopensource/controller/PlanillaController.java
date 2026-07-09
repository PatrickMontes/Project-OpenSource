package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.service.PlanillaService;

import java.util.List;

@RestController
@RequestMapping("/api/planillas")
@Tag(name = "Planillas", description = "Generación de planillas semanales y cálculo del total gastado")
@RequiredArgsConstructor
public class PlanillaController {

    private final PlanillaService planillaService;

    @PostMapping("/generar/{semanaId}")
    public Planilla crearPlanilla(@PathVariable Long semanaId) {
        return planillaService.crearPlanilla(semanaId);
    }

    @GetMapping("/lista")
    public List<Planilla> obtenerPlanillas() {
        return planillaService.obtenerPlanillas();
    }

    @GetMapping("/{id}/detalle")
    public Planilla obtenerPlanilla(@PathVariable Long id) {
        return planillaService.obtenerPlanilla(id);
    }

    @PutMapping("/{id}")
    public Planilla actualizarPlanilla(
            @PathVariable Long id,
            @RequestParam(required = false) Long semanaId
    ) {
        return planillaService.actualizarPlanilla(id, semanaId);
    }

    @PutMapping("/{id}/recalcular-total")
    public Planilla actualizarTotalGastadoPlanilla(@PathVariable Long id) {
        return planillaService.actualizarTotalGastadoPlanilla(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanilla(@PathVariable Long id) {
        planillaService.eliminarPlanilla(id);
    }
}
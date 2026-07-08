package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Pago;
import pe.edu.upc.projectopensource.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Generación, consulta y recálculo de pagos de empleados")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public Pago crearPago(
            @RequestParam Long empleadoId,
            @RequestParam Long planillaId
    ) {
        return pagoService.crearPago(empleadoId, planillaId);
    }

    @GetMapping
    public List<Pago> obtenerPagos() {
        return pagoService.obtenerPagos();
    }

    @GetMapping("/{id}")
    public Pago obtenerPago(@PathVariable Long id) {
        return pagoService.obtenerPago(id);
    }

    @GetMapping("/buscar")
    public List<Pago> buscarPagos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long planillaId,
            @RequestParam(required = false) Long semanaId
    ) {
        return pagoService.buscarPagos(empleadoId, planillaId, semanaId);
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<Pago> obtenerPagosPorEmpleado(@PathVariable Long empleadoId) {
        return pagoService.buscarPagos(empleadoId, null, null);
    }

    @GetMapping("/semana/{semanaId}")
    public List<Pago> obtenerPagosPorSemana(@PathVariable Long semanaId) {
        return pagoService.buscarPagos(null, null, semanaId);
    }

    @PutMapping("/recalcular/empleado/{empleadoId}")
    public Pago actualizarCalculosPago(@PathVariable Long empleadoId) {
        return pagoService.actualizarCalculosPago(empleadoId);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
    }
}
package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.PagoDTO;
import pe.edu.upc.projectopensource.service.PagoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Generación, consulta y recálculo de pagos de empleados")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/crear")
    public ResponseEntity<Response<PagoDTO>> crearPago(
            @RequestParam Long empleadoId,
            @RequestParam Long planillaId
    ) {
        PagoDTO data = pagoService.crearPago(empleadoId, planillaId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<PagoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Pago creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<PagoDTO>>> obtenerPagos() {
        List<PagoDTO> data = pagoService.obtenerPagos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<PagoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de pagos")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<PagoDTO>> obtenerPago(@PathVariable Long id) {
        PagoDTO data = pagoService.obtenerPago(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<PagoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Pago encontrado")
                        .data(data)
                        .build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Response<List<PagoDTO>>> buscarPagos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long planillaId,
            @RequestParam(required = false) Long semanaId
    ) {
        List<PagoDTO> data = pagoService.buscarPagos(empleadoId, planillaId, semanaId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<PagoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Resultados de búsqueda")
                        .data(data)
                        .build());
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<Response<List<PagoDTO>>> obtenerPagosPorEmpleado(@PathVariable Long empleadoId) {
        List<PagoDTO> data = pagoService.buscarPagos(empleadoId, null, null);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<PagoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Pagos del empleado")
                        .data(data)
                        .build());
    }

    @GetMapping("/semana/{semanaId}")
    public ResponseEntity<Response<List<PagoDTO>>> obtenerPagosPorSemana(@PathVariable Long semanaId) {
        List<PagoDTO> data = pagoService.buscarPagos(null, null, semanaId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<PagoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Pagos de la semana")
                        .data(data)
                        .build());
    }

    @PutMapping("/recalcular/empleado/{empleadoId}")
    public ResponseEntity<Response<PagoDTO>> actualizarCalculosPago(@PathVariable Long empleadoId) {
        PagoDTO data = pagoService.actualizarCalculosPago(empleadoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<PagoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Cálculos de pago actualizados exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Pago eliminado exitosamente")
                        .build());
    }
}

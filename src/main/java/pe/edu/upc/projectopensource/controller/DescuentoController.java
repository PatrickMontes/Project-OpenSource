package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.DescuentoDTO;
import pe.edu.upc.projectopensource.service.DescuentoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
@Tag(name = "Descuentos", description = "Gestión de descuentos aplicados a los pagos de empleados")
@RequiredArgsConstructor
public class DescuentoController {

    private final DescuentoService descuentoService;

    @PostMapping("/crear")
    public ResponseEntity<Response<DescuentoDTO>> crearDescuento(@Valid @RequestBody DescuentoDTO descuentoDTO) {
        DescuentoDTO data = descuentoService.crearDescuento(descuentoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<DescuentoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Descuento creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<DescuentoDTO>>> obtenerDescuentos() {
        List<DescuentoDTO> data = descuentoService.obtenerDescuentos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<DescuentoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de descuentos")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<DescuentoDTO>> obtenerDescuento(@PathVariable Long id) {
        DescuentoDTO data = descuentoService.obtenerDescuento(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<DescuentoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Descuento encontrado")
                        .data(data)
                        .build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Response<List<DescuentoDTO>>> buscarDescuentos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long semanaId
    ) {
        List<DescuentoDTO> data = descuentoService.buscarDescuentos(empleadoId, semanaId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<DescuentoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Resultados de búsqueda")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<DescuentoDTO>> actualizarDescuento(
            @PathVariable Long id,
            @Valid @RequestBody DescuentoDTO descuentoDTO
    ) {
        DescuentoDTO data = descuentoService.actualizarDescuento(id, descuentoDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<DescuentoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Descuento actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarDescuento(@PathVariable Long id) {
        descuentoService.eliminarDescuento(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Descuento eliminado exitosamente")
                        .build());
    }
}

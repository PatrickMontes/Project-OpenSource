package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.dto.CargoDTO;
import pe.edu.upc.projectopensource.service.CargoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@Tag(name = "Cargos", description = "Gestión de cargos y sus tarifas")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping("/crear")
    public ResponseEntity<Response<CargoDTO>> crearCargo(@Valid @RequestBody CargoDTO cargoDTO) {
        CargoDTO data = cargoService.crearCargo(cargoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<CargoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Cargo creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<CargoDTO>>> obtenerCargos() {
        List<CargoDTO> data = cargoService.obtenerCargos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<CargoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de cargos")
                        .data(data)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CargoDTO>> obtenerCargo(@PathVariable Long id) {
        CargoDTO data = cargoService.obtenerCargo(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<CargoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Cargo encontrado")
                        .data(data)
                        .build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Response<CargoDTO>> actualizarCargo(
            @PathVariable Long id,
            @Valid @RequestBody CargoDTO cargoDTO
    ) {
        CargoDTO data = cargoService.actualizarCargo(id, cargoDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<CargoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Cargo actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarCargo(@PathVariable Long id) {
        cargoService.eliminarCargo(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Cargo eliminado exitosamente")
                        .build());
    }
}

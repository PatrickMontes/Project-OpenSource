package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.service.CargoService;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@Tag(name = "Cargos", description = "Gestión de cargos y sus tarifas")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping("/crear")
    public Cargo crearCargo(@RequestBody Cargo cargo) {
        return cargoService.crearCargo(cargo);
    }

    @GetMapping
    public List<Cargo> obtenerCargos() {
        return cargoService.obtenerCargos();
    }

    @GetMapping("/{id}")
    public Cargo obtenerCargo(@PathVariable Long id) {
        return cargoService.obtenerCargo(id);
    }

    @PutMapping("/{id}")
    public Cargo actualizarCargo(
            @PathVariable Long id,
            @RequestBody Cargo cargo
    ) {
        return cargoService.actualizarCargo(id, cargo);
    }

    @DeleteMapping("/{id}")
    public void eliminarCargo(@PathVariable Long id) {
        cargoService.eliminarCargo(id);
    }
}
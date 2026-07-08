package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.service.SemanaService;

import java.util.List;

@RestController
@RequestMapping("/api/semanas")
@Tag(name = "Semanas", description = "Gestión de semanas laborales y su estado")
@RequiredArgsConstructor
public class SemanaController {

    private final SemanaService semanaService;

    @PostMapping
    public Semana crearSemana(@RequestBody Semana semana) {
        return semanaService.crearSemana(semana);
    }

    @GetMapping
    public List<Semana> obtenerSemanas() {
        return semanaService.obtenerSemanas();
    }

    @GetMapping("/{id}")
    public Semana obtenerSemana(@PathVariable Long id) {
        return semanaService.obtenerSemana(id);
    }

    @PutMapping("/{id}")
    public Semana actualizarSemana(
            @PathVariable Long id,
            @RequestBody Semana semana
    ) {
        return semanaService.actualizarSemana(id, semana);
    }

    @DeleteMapping("/{id}")
    public void eliminarSemana(@PathVariable Long id) {
        semanaService.eliminarSemana(id);
    }
}
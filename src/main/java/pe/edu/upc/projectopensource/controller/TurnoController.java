package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Turno;
import pe.edu.upc.projectopensource.service.TurnoService;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@Tag(name = "Turnos", description = "Gestión de turnos de trabajo")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public Turno crearTurno(@RequestBody Turno turno) {
        return turnoService.crearTurno(turno);
    }

    @GetMapping
    public List<Turno> obtenerTurnos() {
        return turnoService.obtenerTurnos();
    }

    @GetMapping("/{id}")
    public Turno obtenerTurno(@PathVariable Long id) {
        return turnoService.obtenerTurno(id);
    }

    @PutMapping("/{id}")
    public Turno actualizarTurno(
            @PathVariable Long id,
            @RequestBody Turno turno
    ) {
        return turnoService.actualizarTurno(id, turno);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
    }
}
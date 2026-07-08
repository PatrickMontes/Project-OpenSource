package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Asistencia;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsistenciaType;
import pe.edu.upc.projectopensource.service.AsistenciaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@Tag(name = "Asistencias", description = "Registro y consulta de asistencias de los empleados")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @PostMapping
    public Asistencia crearAsistencia(@RequestBody Asistencia asistencia) {
        return asistenciaService.crearAsistencia(asistencia);
    }

    @GetMapping
    public List<Asistencia> obtenerAsistencias() {
        return asistenciaService.obtenerAsistencias();
    }

    @GetMapping("/{id}")
    public Asistencia obtenerAsistencia(@PathVariable Long id) {
        return asistenciaService.obtenerAsistencia(id);
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<Asistencia> obtenerAsistenciasPorEmpleado(@PathVariable Long empleadoId) {
        return asistenciaService.buscarAsistencias(empleadoId, null, null, null, null);
    }

    @GetMapping("/buscar")
    public List<Asistencia> buscarAsistencias(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long semanaId,
            @RequestParam(required = false) EstadoAsistenciaType estado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        return asistenciaService.buscarAsistencias(empleadoId, semanaId, estado, fechaInicio, fechaFin);
    }

    @PutMapping("/{id}")
    public Asistencia actualizarAsistencia(
            @PathVariable Long id,
            @RequestBody Asistencia asistencia
    ) {
        return asistenciaService.actualizarAsistencia(id, asistencia);
    }

    @DeleteMapping("/{id}")
    public void eliminarAsistencia(@PathVariable Long id) {
        asistenciaService.eliminarAsistencia(id);
    }
}
package pe.edu.upc.projectopensource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Descuento;
import pe.edu.upc.projectopensource.service.DescuentoService;

import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
@RequiredArgsConstructor
public class DescuentoController {

    private final DescuentoService descuentoService;

    @PostMapping
    public Descuento crearDescuento(@RequestBody Descuento descuento) {
        return descuentoService.crearDescuento(descuento);
    }

    @GetMapping
    public List<Descuento> obtenerDescuentos() {
        return descuentoService.obtenerDescuentos();
    }

    @GetMapping("/{id}")
    public Descuento obtenerDescuento(@PathVariable Long id) {
        return descuentoService.obtenerDescuento(id);
    }

    @GetMapping("/buscar")
    public List<Descuento> buscarDescuentos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Long semanaId
    ) {
        return descuentoService.buscarDescuentos(empleadoId, semanaId);
    }

    @PutMapping("/{id}")
    public Descuento actualizarDescuento(
            @PathVariable Long id,
            @RequestBody Descuento descuento
    ) {
        return descuentoService.actualizarDescuento(id, descuento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDescuento(@PathVariable Long id) {
        descuentoService.eliminarDescuento(id);
    }
}
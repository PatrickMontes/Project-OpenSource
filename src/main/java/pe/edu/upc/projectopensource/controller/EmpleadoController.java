package pe.edu.upc.projectopensource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Direccion;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.ExamenMedico;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;
import pe.edu.upc.projectopensource.service.EmpleadoService;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @GetMapping
    public List<Empleado> obtenerEmpleados() {
        return empleadoService.obtenerEmpleados();
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleado(@PathVariable Long id) {
        return empleadoService.obtenerEmpleado(id);
    }

    @GetMapping("/buscar")
    public List<Empleado> buscarEmpleados(
            @RequestParam(required = false) Integer dni,
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) SexoType sexo,
            @RequestParam(required = false) EstadoType estado,
            @RequestParam(required = false) Boolean asegurado
    ) {
        return empleadoService.buscarEmpleados(
                dni,
                nombres,
                apellidos,
                sexo,
                estado,
                asegurado
        );
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(
            @PathVariable Long id,
            @RequestBody Empleado empleado
    ) {
        return empleadoService.actualizarEmpleado(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
    }

    @PutMapping("/{id}/direccion")
    public Empleado actualizarDireccion(
            @PathVariable Long id,
            @RequestBody Direccion direccion
    ) {
        return empleadoService.actualizarDireccionEmpleado(id, direccion);
    }

    @PutMapping("/{id}/examen-medico")
    public Empleado actualizarExamenMedico(
            @PathVariable Long id,
            @RequestBody ExamenMedico examenMedico
    ) {
        return empleadoService.actualizarExamenMedicoEmpleado(id, examenMedico);
    }
}
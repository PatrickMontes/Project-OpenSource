package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.entity.Direccion;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.ExamenMedico;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;
import pe.edu.upc.projectopensource.repository.CargoRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final CargoRepository cargoRepository;
    private final EmpleadoRepository empleadoRepository;

    public Empleado crearEmpleado(Empleado empleado){
        Cargo cargo = cargoRepository.findById(empleado.getCargo().getId()).orElseThrow(
                () -> new RuntimeException("Cargo no encontrado"));

        empleado.setCargo(cargo);

        return empleadoRepository.save(empleado);
    }


    public List<Empleado> obtenerEmpleados (){
        return empleadoRepository.findAll();
    }


    public List<Empleado> buscarEmpleados(Integer dni, String nombres, String apellidos, SexoType sexo, EstadoType estado, Boolean asegurado){
        return empleadoRepository.buscarEmpleados(dni, nombres, apellidos, sexo, estado, asegurado);
    }


    public Empleado obtenerEmpleado(Long id){
        return empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));
    }


    public Empleado actualizarEmpleado(Long id, Empleado empleadoActualizada){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        empleado.setDni(empleadoActualizada.getDni());
        empleado.setNombres(empleadoActualizada.getNombres());
        empleado.setApellidos(empleadoActualizada.getApellidos());
        empleado.setEdad(empleadoActualizada.getEdad());
        empleado.setSexo(empleadoActualizada.getSexo());
        empleado.setFechaNacimiento(empleadoActualizada.getFechaNacimiento());
        empleado.setCorreo(empleadoActualizada.getCorreo());
        empleado.setCelular(empleadoActualizada.getCelular());
        empleado.setAsegurado(empleadoActualizada.getAsegurado());
        empleado.setCargo(empleadoActualizada.getCargo());
        empleado.setEstado(empleadoActualizada.getEstado());

        return empleadoRepository.save(empleado);
    }


    public void eliminarEmpleado(Long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        empleadoRepository.delete(empleado);
    }


    public Empleado actualizarDireccionEmpleado(Long id, Direccion direccionActualizada){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        if (empleado.getDireccion() == null) {
            empleado.setDireccion(new Direccion());
        }

        Direccion direccion = empleado.getDireccion();

        if (direccionActualizada.getDepartamento() != null) direccion.setDepartamento(direccionActualizada.getDepartamento());
        if (direccionActualizada.getProvincia() != null) direccion.setProvincia(direccionActualizada.getProvincia());
        if (direccionActualizada.getDistrito() != null) direccion.setDistrito(direccionActualizada.getDistrito());
        if (direccionActualizada.getDomicilio() != null) direccion.setDomicilio(direccionActualizada.getDomicilio());

        return empleadoRepository.save(empleado);
    }


    public Empleado actualizarExamenMedicoEmpleado(Long id, ExamenMedico examenMedicoActualizada){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (empleado.getExamenMedico() == null) {
            empleado.setExamenMedico(new ExamenMedico());
        }

        ExamenMedico examenMedico = empleado.getExamenMedico();

        if (examenMedicoActualizada.getFecha() != null) examenMedico.setFecha(examenMedicoActualizada.getFecha());
        if (examenMedicoActualizada.getMontoGastado() != null) examenMedico.setMontoGastado(examenMedicoActualizada.getMontoGastado());
        if (examenMedicoActualizada.getAlta() != null) examenMedico.setAlta(examenMedicoActualizada.getAlta());

        return empleadoRepository.save(empleado);
    }
}

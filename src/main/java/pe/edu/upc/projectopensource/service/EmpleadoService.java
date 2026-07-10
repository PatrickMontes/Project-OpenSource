package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.DireccionDTO;
import pe.edu.upc.projectopensource.dto.EmpleadoDTO;
import pe.edu.upc.projectopensource.dto.ExamenMedicoDTO;
import pe.edu.upc.projectopensource.entity.Cargo;
import pe.edu.upc.projectopensource.entity.Direccion;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.ExamenMedico;
import pe.edu.upc.projectopensource.entity.enums.EstadoType;
import pe.edu.upc.projectopensource.entity.enums.SexoType;
import pe.edu.upc.projectopensource.mapper.DireccionMapper;
import pe.edu.upc.projectopensource.mapper.EmpleadoMapper;
import pe.edu.upc.projectopensource.mapper.ExamenMedicoMapper;
import pe.edu.upc.projectopensource.repository.CargoRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final CargoRepository cargoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final DireccionMapper direccionMapper;
    private final ExamenMedicoMapper examenMedicoMapper;

    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO){
        Cargo cargo = cargoRepository.findById(empleadoDTO.getCargoId()).orElseThrow(
                () -> new RuntimeException("Cargo no encontrado"));

        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        empleado.setCargo(cargo);

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }


    public List<EmpleadoDTO> obtenerEmpleados(){
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDto)
                .toList();
    }


    public List<EmpleadoDTO> buscarEmpleados(Integer dni, String nombres, String apellidos, SexoType sexo, EstadoType estado, Boolean asegurado){
        return empleadoRepository.buscarEmpleados(dni, nombres, apellidos, sexo, estado, asegurado).stream()
                .map(empleadoMapper::toDto)
                .toList();
    }


    public EmpleadoDTO obtenerEmpleado(Long id){
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }


    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        empleado.setDni(empleadoDTO.getDni());
        empleado.setNombres(empleadoDTO.getNombres());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setEdad(empleadoDTO.getEdad());
        empleado.setSexo(empleadoDTO.getSexo());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setCorreo(empleadoDTO.getCorreo());
        empleado.setCelular(empleadoDTO.getCelular());
        empleado.setAsegurado(empleadoDTO.getAsegurado());
        empleado.setEstado(empleadoDTO.getEstado());

        if (empleadoDTO.getCargoId() != null) {
            Cargo cargo = cargoRepository.findById(empleadoDTO.getCargoId())
                    .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
            empleado.setCargo(cargo);
        }

        if (empleadoDTO.getDireccion() != null) {
            empleado.setDireccion(direccionMapper.toEntity(empleadoDTO.getDireccion()));
        }

        if (empleadoDTO.getExamenMedico() != null) {
            empleado.setExamenMedico(examenMedicoMapper.toEntity(empleadoDTO.getExamenMedico()));
        }

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }


    public void eliminarEmpleado(Long id){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        empleadoRepository.delete(empleado);
    }


    public EmpleadoDTO actualizarDireccionEmpleado(Long id, DireccionDTO direccionDTO){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        Direccion direccion = empleado.getDireccion();
        if (direccion == null) {
            direccion = new Direccion();
            empleado.setDireccion(direccion);
        }

        if (direccionDTO.getDepartamento() != null) direccion.setDepartamento(direccionDTO.getDepartamento());
        if (direccionDTO.getProvincia() != null) direccion.setProvincia(direccionDTO.getProvincia());
        if (direccionDTO.getDistrito() != null) direccion.setDistrito(direccionDTO.getDistrito());
        if (direccionDTO.getDomicilio() != null) direccion.setDomicilio(direccionDTO.getDomicilio());

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }


    public EmpleadoDTO actualizarExamenMedicoEmpleado(Long id, ExamenMedicoDTO examenMedicoDTO){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        ExamenMedico examenMedico = empleado.getExamenMedico();
        if (examenMedico == null) {
            examenMedico = new ExamenMedico();
            empleado.setExamenMedico(examenMedico);
        }

        if (examenMedicoDTO.getFecha() != null) examenMedico.setFecha(examenMedicoDTO.getFecha());
        if (examenMedicoDTO.getMontoGastado() != null) examenMedico.setMontoGastado(examenMedicoDTO.getMontoGastado());
        if (examenMedicoDTO.getAlta() != null) examenMedico.setAlta(examenMedicoDTO.getAlta());

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }
}

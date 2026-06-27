package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Asistencia;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsisteciaType;
import pe.edu.upc.projectopensource.repository.AsistenciaRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;
import pe.edu.upc.projectopensource.repository.SemanaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final SemanaRepository semanaRepository;


    public Asistencia crearAsistencia(Asistencia asistencia){
        Empleado empleado = empleadoRepository.findById(asistencia.getEmpleado().getId()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        Semana semana = semanaRepository.findById(asistencia.getSemana().getId()).orElseThrow(
                () -> new RuntimeException(("Semana no encontrada")));

        if(asistencia.getFecha() == null){
            throw new RuntimeException("La fecha es obligatoria");
        }

        if(asistencia.getMinutosExtras() < 0){
            throw new RuntimeException(("Los minutos extras no pueden ser negativos"));
        }

        return asistenciaRepository.save(asistencia);
    }


    public List<Asistencia> obtenerAsistencias (){
        List<Asistencia> asistencias = asistenciaRepository.findAll();

        if (asistencias.isEmpty()){
            throw new RuntimeException(("No existen asistencias registradas"));
        }

        return asistencias;
    }


    public List<Asistencia> buscarAsistencias(Long empleadoId, Long semanaId, EstadoAsisteciaType estado, LocalDate fechaInicio, LocalDate fechaFin){
        if(!empleadoRepository.existsById(empleadoId)){
            throw new RuntimeException("Empleado no existe");
        }

        if (!semanaRepository.existsById(semanaId)){
            throw new RuntimeException("Semana no existe");
        }

        return asistenciaRepository.findAsistencias(empleadoId, semanaId, estado, fechaInicio, fechaFin);
    }


    public Asistencia obtenerAsistencia(Long id){
        return asistenciaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Asistencia no encontrada"));
    }


    public Asistencia actualizarAsistencia(Long id, Asistencia asistenciaActualizada){
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Asistencia no encontrada"));

        asistencia.setEmpleado(asistenciaActualizada.getEmpleado());
        asistencia.setSemana(asistenciaActualizada.getSemana());
        asistencia.setFecha(asistenciaActualizada.getFecha());
        asistencia.setEstado(asistenciaActualizada.getEstado());
        asistencia.setMinutosExtras(asistenciaActualizada.getMinutosExtras());
        asistencia.setMinutosAtrasadas(asistenciaActualizada.getMinutosAtrasadas());
        asistencia.setFechaModificacion(LocalDateTime.now());
        asistencia.setObservaciones(asistenciaActualizada.getObservaciones());


        return asistenciaRepository.save(asistencia);
    }


    public void eliminarAsistencia(Long id){
        if(!asistenciaRepository.existsById(id)){
            throw new RuntimeException("Asistencia no encontrada");
        }

        asistenciaRepository.deleteById(id);
    }
}

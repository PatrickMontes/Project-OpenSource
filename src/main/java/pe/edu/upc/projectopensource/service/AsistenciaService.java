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

        asistencia.setEmpleado(empleado);
        asistencia.setSemana(semana);

        return asistenciaRepository.save(asistencia);
    }


    public List<Asistencia> obtenerAsistencias (){
        return asistenciaRepository.findAll();
    }


    public List<Asistencia> buscarAsistencias(Long empleadoId, Long semanaId, EstadoAsisteciaType estado, LocalDate fechaInicio, LocalDate fechaFin){
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
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Asistencia no encontrada"));

        asistenciaRepository.delete(asistencia);
    }
}

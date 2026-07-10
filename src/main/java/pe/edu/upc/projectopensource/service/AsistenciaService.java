package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.AsistenciaDTO;
import pe.edu.upc.projectopensource.entity.Asistencia;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.entity.enums.EstadoAsistenciaType;
import pe.edu.upc.projectopensource.mapper.AsistenciaMapper;
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
    private final AsistenciaMapper asistenciaMapper;


    public AsistenciaDTO crearAsistencia(AsistenciaDTO asistenciaDTO){
        Empleado empleado = empleadoRepository.findById(asistenciaDTO.getEmpleadoId()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        Semana semana = semanaRepository.findById(asistenciaDTO.getSemanaId()).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));

        Asistencia asistencia = asistenciaMapper.toEntity(asistenciaDTO);
        asistencia.setEmpleado(empleado);
        asistencia.setSemana(semana);

        return asistenciaMapper.toDto(asistenciaRepository.save(asistencia));
    }


    public List<AsistenciaDTO> obtenerAsistencias(){
        return asistenciaRepository.findAll().stream()
                .map(asistenciaMapper::toDto)
                .toList();
    }


    public List<AsistenciaDTO> buscarAsistencias(Long empleadoId, Long semanaId, EstadoAsistenciaType estado, LocalDate fechaInicio, LocalDate fechaFin){
        return asistenciaRepository.findAsistencias(empleadoId, semanaId, estado, fechaInicio, fechaFin).stream()
                .map(asistenciaMapper::toDto)
                .toList();
    }


    public AsistenciaDTO obtenerAsistencia(Long id){
        return asistenciaRepository.findById(id)
                .map(asistenciaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
    }


    public AsistenciaDTO actualizarAsistencia(Long id, AsistenciaDTO asistenciaDTO){
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Asistencia no encontrada"));

        if (asistenciaDTO.getEmpleadoId() != null) {
            Empleado empleado = empleadoRepository.findById(asistenciaDTO.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            asistencia.setEmpleado(empleado);
        }

        if (asistenciaDTO.getSemanaId() != null) {
            Semana semana = semanaRepository.findById(asistenciaDTO.getSemanaId())
                    .orElseThrow(() -> new RuntimeException("Semana no encontrada"));
            asistencia.setSemana(semana);
        }

        asistencia.setFecha(asistenciaDTO.getFecha());
        asistencia.setEstado(asistenciaDTO.getEstado());
        asistencia.setMinutosExtras(asistenciaDTO.getMinutosExtras());
        asistencia.setMinutosAtrasadas(asistenciaDTO.getMinutosAtrasadas());
        asistencia.setFechaModificacion(LocalDateTime.now());
        asistencia.setObservaciones(asistenciaDTO.getObservaciones());

        return asistenciaMapper.toDto(asistenciaRepository.save(asistencia));
    }


    public void eliminarAsistencia(Long id){
        Asistencia asistencia = asistenciaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Asistencia no encontrada"));

        asistenciaRepository.delete(asistencia);
    }
}

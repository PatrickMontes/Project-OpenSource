package pe.edu.upc.projectopensource.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.SemanaDTO;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.entity.Turno;
import pe.edu.upc.projectopensource.mapper.SemanaMapper;
import pe.edu.upc.projectopensource.repository.SemanaRepository;
import pe.edu.upc.projectopensource.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemanaService {
    private final TurnoRepository turnoRepository;
    private final SemanaRepository semanaRepository;
    private final SemanaMapper semanaMapper;


    public SemanaDTO crearSemana(SemanaDTO semanaDTO){
        Turno turno = turnoRepository.findById(semanaDTO.getTurnoId()).orElseThrow(
                () -> new RuntimeException("Turno no encontrado"));

        Semana semana = semanaMapper.toEntity(semanaDTO);
        semana.setTurno(turno);

        return semanaMapper.toDto(semanaRepository.save(semana));
    }


    public List<SemanaDTO> obtenerSemanas(){
        return semanaRepository.findAll().stream()
                .map(semanaMapper::toDto)
                .toList();
    }


    public SemanaDTO obtenerSemana(Long id){
        return semanaRepository.findById(id)
                .map(semanaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Semana no encontrada"));
    }


    public SemanaDTO actualizarSemana(Long id, SemanaDTO semanaDTO){
        Semana semana = semanaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));

        semana.setFechaInicio(semanaDTO.getFechaInicio());
        semana.setFechaFin(semanaDTO.getFechaFin());
        semana.setNumeroSemana(semanaDTO.getNumeroSemana());
        semana.setEstado(semanaDTO.getEstado());

        if (semanaDTO.getTurnoId() != null) {
            Turno turno = turnoRepository.findById(semanaDTO.getTurnoId())
                    .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
            semana.setTurno(turno);
        }

        return semanaMapper.toDto(semanaRepository.save(semana));
    }


    public void eliminarSemana(Long id){
        Semana semana = semanaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));

        semanaRepository.delete(semana);
    }
}

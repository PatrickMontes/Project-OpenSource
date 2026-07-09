package pe.edu.upc.projectopensource.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.entity.Turno;
import pe.edu.upc.projectopensource.repository.SemanaRepository;
import pe.edu.upc.projectopensource.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemanaService {
    private final TurnoRepository turnoRepository;
    private final SemanaRepository semanaRepository;


    @Transactional
    public Semana crearSemana(Semana semana){
        Long turnoId = semana.getTurnoId();
        if (turnoId == null) {
            throw new IllegalArgumentException("El campo 'turnoId' es obligatorio");
        }
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        semana.setTurno(turno);
        return semanaRepository.save(semana);
    }


    public List<Semana> obtenerSemanas(){
        return semanaRepository.findAll();
    }


    public Semana obtenerSemana(Long id){
        return semanaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));
    }


    public Semana actualizarSemana(Long id, Semana semanaActualizada){
        Semana semana = semanaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));

        semana.setFechaInicio(semanaActualizada.getFechaInicio());
        semana.setFechaFin(semanaActualizada.getFechaFin());
        semana.setNumeroSemana(semanaActualizada.getNumeroSemana());
        semana.setTurno(semanaActualizada.getTurno());
        semana.setEstado(semanaActualizada.getEstado());

        return semanaRepository.save(semana);
    }


    public void eliminarSemana(Long id){
        Semana semana = semanaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Semana no encontrada"));

        semanaRepository.delete(semana);
    }
}

package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Turno;
import pe.edu.upc.projectopensource.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {
    private final TurnoRepository turnoRepository;


    public Turno crearTurno(Turno turno){
        return turnoRepository.save(turno);
    }


    public List<Turno> obtenerTurnos(){
        return turnoRepository.findAll();
    }


    public Turno obtenerTurno(Long id){
        return turnoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Turno no encontrado"));
    }


    public Turno actualizarTurno(Long id, Turno turnoActualizada){
        Turno turno = turnoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Turno no encontrao"));

        turno.setTipo(turnoActualizada.getTipo());
        turno.setHoraInicio(turnoActualizada.getHoraInicio());
        turno.setHoraFin(turnoActualizada.getHoraFin());

        return turnoRepository.save(turno);
    }


    public void eliminarTurno(Long id){
        Turno turno = turnoRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Turno no encontrado"));

        turnoRepository.delete(turno);
    }
}

package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.TurnoDTO;
import pe.edu.upc.projectopensource.entity.Turno;
import pe.edu.upc.projectopensource.mapper.TurnoMapper;
import pe.edu.upc.projectopensource.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final TurnoMapper turnoMapper;


    public TurnoDTO crearTurno(TurnoDTO turnoDTO){
        Turno turno = turnoMapper.toEntity(turnoDTO);
        return turnoMapper.toDto(turnoRepository.save(turno));
    }


    public List<TurnoDTO> obtenerTurnos(){
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toDto)
                .toList();
    }


    public TurnoDTO obtenerTurno(Long id){
        return turnoRepository.findById(id)
                .map(turnoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }


    public TurnoDTO actualizarTurno(Long id, TurnoDTO turnoDTO){
        Turno turno = turnoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Turno no encontrado"));

        turno.setTipo(turnoDTO.getTipo());
        turno.setHoraInicio(turnoDTO.getHoraInicio());
        turno.setHoraFin(turnoDTO.getHoraFin());

        return turnoMapper.toDto(turnoRepository.save(turno));
    }


    public void eliminarTurno(Long id){
        Turno turno = turnoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Turno no encontrado"));

        turnoRepository.delete(turno);
    }
}

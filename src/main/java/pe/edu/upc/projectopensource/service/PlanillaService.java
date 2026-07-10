package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.PlanillaDTO;
import pe.edu.upc.projectopensource.dto.SemanaDTO;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.mapper.PlanillaMapper;
import pe.edu.upc.projectopensource.mapper.SemanaMapper;
import pe.edu.upc.projectopensource.repository.PlanillaRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanillaService {
    private final SemanaService semanaService;
    private final PlanillaRepository planillaRepository;
    private final PlanillaMapper planillaMapper;
    private final SemanaMapper semanaMapper;

    @Lazy
    private final CalculoFinancieroService calculoFinancieroService;


    public PlanillaDTO crearPlanilla(Long semanaId){
        SemanaDTO semanaDTO = semanaService.obtenerSemana(semanaId);
        Semana semana = semanaMapper.toEntity(semanaDTO);

        Planilla planilla = new Planilla();
        planilla.setSemana(semana);
        planilla.setTotalGastado(BigDecimal.ZERO);

        Planilla planillaGuardada = planillaRepository.save(planilla);
        BigDecimal totalGastado = calculoFinancieroService.calcularGastoTotalPlanilla(planillaGuardada.getId());

        if (totalGastado.compareTo(BigDecimal.ZERO) > 0) {
            planillaGuardada.setTotalGastado(totalGastado);
            planillaRepository.save(planillaGuardada);
        }

        return planillaMapper.toDto(planillaGuardada);
    }


    public List<PlanillaDTO> obtenerPlanillas(){
        return planillaRepository.findAll().stream()
                .map(planillaMapper::toDto)
                .toList();
    }


    public PlanillaDTO obtenerPlanilla(Long id){
        return planillaRepository.findById(id)
                .map(planillaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Planilla no encontrada"));
    }


    public PlanillaDTO actualizarPlanilla(Long id, Long semanaId){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        if (semanaId != null) {
            SemanaDTO semanaDTO = semanaService.obtenerSemana(semanaId);
            planilla.setSemana(semanaMapper.toEntity(semanaDTO));
        }

        return planillaMapper.toDto(planillaRepository.save(planilla));
    }


    public void eliminarPlanilla(Long id){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        planillaRepository.delete(planilla);
    }


    public PlanillaDTO actualizarTotalGastadoPlanilla(Long id){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        BigDecimal totalGastado = calculoFinancieroService.calcularGastoTotalPlanilla(id);
        planilla.setTotalGastado(totalGastado);

        return planillaMapper.toDto(planillaRepository.save(planilla));
    }
}

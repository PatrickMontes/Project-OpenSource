package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.repository.PlanillaRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanillaService {
    private final SemanaService semanaService;
    private final PlanillaRepository planillaRepository;

    @Lazy
    private final CalculoFinancieroService calculoFinancieroService;


    public Planilla crearPlanilla(Long semanaId){
        Semana semana = semanaService.obtenerSemana(semanaId);

        Planilla planilla = new Planilla();
        planilla.setSemana(semana);
        planilla.setTotalGastado(BigDecimal.ZERO);

        Planilla planillaGuardada = planillaRepository.save(planilla);
        BigDecimal totalGastado = calculoFinancieroService.calcularGastoTotalPlanilla(planillaGuardada.getId());

        if (totalGastado.compareTo(BigDecimal.ZERO) > 0) {
            planillaGuardada.setTotalGastado(totalGastado);
            planillaRepository.save(planillaGuardada);
        }

        return planillaGuardada;
    }


    public List<Planilla> obtenerPlanillas(){
        return planillaRepository.findAll();
    }


    public Planilla obtenerPlanilla(Long id){
        return planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));
    }


    public Planilla actualizarPlanilla(Long id, Long semanaId){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        if (semanaId != null) {
            Semana nuevaSemana = semanaService.obtenerSemana(semanaId);
            planilla.setSemana(nuevaSemana);
        }

        return planillaRepository.save(planilla);
    }


    public void eliminarPlanilla(Long id){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        planillaRepository.delete(planilla);
    }


    public Planilla actualizarTotalGastadoPlanilla(Long id){
        Planilla planilla = planillaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Planilla no encontrada"));

        BigDecimal totalGastado = calculoFinancieroService.calcularGastoTotalPlanilla(id);
        planilla.setTotalGastado(totalGastado);

        return planillaRepository.save(planilla);
    }
}

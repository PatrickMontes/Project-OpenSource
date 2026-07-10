package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculoFinancieroService {
    private final AsistenciaService asistenciaService;
    private final DescuentoService descuentoService;
    private final EmpleadoService empleadoService;
    private final CargoService cargoService;
    private final PagoService pagoService;
    private final PlanillaService planillaService;


    public BigDecimal obtenerPagoBruto(Long empleadoId){
        EmpleadoDTO empleado = empleadoService.obtenerEmpleado(empleadoId);
        CargoDTO cargo = cargoService.obtenerCargo(empleado.getCargoId());

        return cargo.getSalarioSemanal();
    }


    public Integer calcularMinutosExtrasTotales(Long empleadoId, Long planillaId){
        PlanillaDTO planilla = planillaService.obtenerPlanilla(planillaId);

        List<AsistenciaDTO> asistencias = asistenciaService.buscarAsistencias(
                empleadoId, planilla.getSemanaId(), null, null, null);

        return asistencias.stream()
                .mapToInt(AsistenciaDTO::getMinutosExtras)
                .sum();
    }


    public BigDecimal calcularDescuentoTotal(Long empleadoId, Long semanaId) {
        List<DescuentoDTO> descuentos = descuentoService.buscarDescuentos(empleadoId, semanaId);

        return descuentos.stream()
                .map(DescuentoDTO::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calcularPagoNeto(Long empleadoId, Long planillaId) {
        PlanillaDTO planilla = planillaService.obtenerPlanilla(planillaId);

        BigDecimal salarioBruto = this.obtenerPagoBruto(empleadoId);
        BigDecimal descuentoTotal = this.calcularDescuentoTotal(empleadoId, planilla.getSemanaId());

        return salarioBruto.subtract(descuentoTotal);
    }


    public BigDecimal calcularGastoTotalPlanilla(Long planillaId) {
        List<PagoDTO> pagos = pagoService.buscarPagos(null, planillaId, null);

        return pagos.stream()
                .map(PagoDTO::getPagoNeto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

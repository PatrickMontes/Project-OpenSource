package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculoFinancieroService {
    private final AsistenciaService asistenciaService;
    private final DescuentoService descuentoService;
    private final EmpleadoService empleadoService;

    @Lazy
    private final PlanillaService planillaService;
    private final CargoService cargoService;

    @Lazy
    private final PagoService pagoService;


    public BigDecimal obtenerPagoBruto(Long empleadoId){
        Empleado empleado = empleadoService.obtenerEmpleado(empleadoId);
        Cargo cargo = cargoService.obtenerCargo(empleado.getCargo().getId());

        return cargo.getSalarioSemanal();
    }


    public Integer calcularMinutosExtrasTotales(Long empleadoId, Long planillaId){
        Planilla planilla = planillaService.obtenerPlanilla(planillaId);

        List<Asistencia> asistencias = asistenciaService.buscarAsistencias(
                empleadoId, planilla.getSemana().getId(), null, null, null);

        return asistencias.stream()
                .mapToInt(Asistencia::getMinutosExtras)
                .sum();
    }


    public BigDecimal calcularDescuentoTotal(Long empleadoId, Long semanaId) {
        List<Descuento> descuentos = descuentoService.buscarDescuentos(empleadoId, semanaId);

        return descuentos.stream()
                .map(Descuento::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calcularPagoNeto(Long empleadoId, Long planillaId) {
        Planilla planilla = planillaService.obtenerPlanilla(planillaId);

        BigDecimal salarioBruto = this.obtenerPagoBruto(empleadoId);
        BigDecimal descuentoTotal = this.calcularDescuentoTotal(empleadoId, planilla.getSemana().getId());

        return salarioBruto.subtract(descuentoTotal);
    }


    public BigDecimal calcularGastoTotalPlanilla(Long planillaId) {
        List<Pago> pagos = pagoService.buscarPagos(null, planillaId, null);

        return pagos.stream()
                .map(Pago::getPagoNeto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

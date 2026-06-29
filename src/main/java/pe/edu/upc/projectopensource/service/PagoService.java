package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Pago;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.entity.enums.PagoCreadoEvent;
import pe.edu.upc.projectopensource.repository.PagoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;

    @Lazy
    private final PlanillaService planillaService;
    private final  EmpleadoService empleadoService;
    private final ApplicationEventPublisher eventPublisher;

    @Lazy
    private final CalculoFinancieroService calculoFinancieroService;



    public Pago crearPago(Long empleadoId, Long planillaId){
        Pago pago = new Pago();

        Empleado empleado = empleadoService.obtenerEmpleado(empleadoId);
        Planilla planilla = planillaService.obtenerPlanilla(planillaId);

        pago.setEmpleado(empleado);
        pago.setPlanilla(planilla);

        BigDecimal pagoBruto = calculoFinancieroService.obtenerPagoBruto(empleadoId);
        Integer minutosExtras = calculoFinancieroService.calcularMinutosExtrasTotales(empleadoId, planillaId);
        BigDecimal pagoNeto = calculoFinancieroService.calcularPagoNeto(empleadoId, planillaId);

        pago.setPagoBruto(pagoBruto);
        pago.setMinutosExtrasTotales(minutosExtras);
        pago.setPagoNeto(pagoNeto);

        Pago pagoGuardado = pagoRepository.save(pago);

        eventPublisher.publishEvent(new PagoCreadoEvent(planillaId));

        return pagoGuardado;
    }


    public List<Pago> buscarPagos(Long empleadoId, Long planillaId, Long semanaId){
        return pagoRepository.findPagos(empleadoId, planillaId, semanaId);
    }


    public List<Pago> obtenerPagos(){
        return pagoRepository.findAll();
    }


    public Pago obtenerPago(Long id){
        return pagoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado"));
    }


    public void eliminarPago(Long id){
        Pago pago = pagoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado"));
    }


    public Pago actualizarCalculosPago(Long empleadoId){
        Pago pago = pagoRepository.findFirstByEmpleadoIdOrderByIdDesc(empleadoId)
                .orElseThrow(() -> new RuntimeException("El empleado con el id " + empleadoId + " no tiene un pago"));

        Planilla planilla = pago.getPlanilla();

        BigDecimal pagoBruto = calculoFinancieroService.obtenerPagoBruto(empleadoId);
        Integer minutosExtras = calculoFinancieroService.calcularMinutosExtrasTotales(empleadoId, planilla.getId());
        BigDecimal pagoNeto = calculoFinancieroService.calcularPagoNeto(empleadoId, planilla.getId());

        pago.setPagoBruto(pagoBruto);
        pago.setMinutosExtrasTotales(minutosExtras);
        pago.setPagoNeto(pagoNeto);

        return pagoRepository.save(pago);
    }
}

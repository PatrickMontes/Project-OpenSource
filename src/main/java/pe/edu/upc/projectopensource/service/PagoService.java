package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.EmpleadoDTO;
import pe.edu.upc.projectopensource.dto.PagoDTO;
import pe.edu.upc.projectopensource.dto.PlanillaDTO;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Pago;
import pe.edu.upc.projectopensource.entity.Planilla;
import pe.edu.upc.projectopensource.entity.enums.PagoCreadoEvent;
import pe.edu.upc.projectopensource.mapper.EmpleadoMapper;
import pe.edu.upc.projectopensource.mapper.PagoMapper;
import pe.edu.upc.projectopensource.mapper.PlanillaMapper;
import pe.edu.upc.projectopensource.repository.PagoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final EmpleadoMapper empleadoMapper;
    private final PlanillaMapper planillaMapper;

    @Lazy
    private final PlanillaService planillaService;
    private final  EmpleadoService empleadoService;
    private final ApplicationEventPublisher eventPublisher;

    @Lazy
    private final CalculoFinancieroService calculoFinancieroService;



    public PagoDTO crearPago(Long empleadoId, Long planillaId){
        EmpleadoDTO empleadoDTO = empleadoService.obtenerEmpleado(empleadoId);
        PlanillaDTO planillaDTO = planillaService.obtenerPlanilla(planillaId);

        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        Planilla planilla = planillaMapper.toEntity(planillaDTO);

        Pago pago = new Pago();
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

        return pagoMapper.toDto(pagoGuardado);
    }


    public List<PagoDTO> buscarPagos(Long empleadoId, Long planillaId, Long semanaId){
        return pagoRepository.findPagos(empleadoId, planillaId, semanaId).stream()
                .map(pagoMapper::toDto)
                .toList();
    }


    public List<PagoDTO> obtenerPagos(){
        return pagoRepository.findAll().stream()
                .map(pagoMapper::toDto)
                .toList();
    }


    public PagoDTO obtenerPago(Long id){
        return pagoRepository.findById(id)
                .map(pagoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }


    public void eliminarPago(Long id){
        Pago pago = pagoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado"));
    }


    public PagoDTO actualizarCalculosPago(Long empleadoId){
        Pago pago = pagoRepository.findFirstByEmpleadoIdOrderByIdDesc(empleadoId)
                .orElseThrow(() -> new RuntimeException("El empleado con el id " + empleadoId + " no tiene un pago"));

        Planilla planilla = pago.getPlanilla();

        BigDecimal pagoBruto = calculoFinancieroService.obtenerPagoBruto(empleadoId);
        Integer minutosExtras = calculoFinancieroService.calcularMinutosExtrasTotales(empleadoId, planilla.getId());
        BigDecimal pagoNeto = calculoFinancieroService.calcularPagoNeto(empleadoId, planilla.getId());

        pago.setPagoBruto(pagoBruto);
        pago.setMinutosExtrasTotales(minutosExtras);
        pago.setPagoNeto(pagoNeto);

        return pagoMapper.toDto(pagoRepository.save(pago));
    }
}

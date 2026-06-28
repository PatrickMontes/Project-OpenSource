package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Descuento;
import pe.edu.upc.projectopensource.repository.DescuentoRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;
import pe.edu.upc.projectopensource.repository.SemanaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescuentoService {
    private final SemanaRepository semanaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final DescuentoRepository descuentoRepository;


    public Descuento crearDescuento(Descuento descuento){
        empleadoRepository.findById(descuento.getEmpleado().getId()).orElseThrow(
                () -> new RuntimeException("No se encontró el empleado"));

        semanaRepository.findById(descuento.getSemana().getId()).orElseThrow(
                () -> new RuntimeException("No se encontró la semana"));

        if(descuento.getMonto() == null || descuento.getMonto().doubleValue() <= 0){
            throw new RuntimeException("El monto del descuento es obligatorio y debe ser un valor positivo");
        }

        if(descuento.getRazon() == null || descuento.getRazon().isEmpty()){
            throw new RuntimeException("La razón del descuento es obligatoria");
        }

        return descuentoRepository.save(descuento);
    }


    public List<Descuento> obtenerDescuentos(){
        List<Descuento> descuentos = descuentoRepository.findAll();

        if(descuentos.isEmpty()){
            throw new RuntimeException("No existen descuentos registrados");
        }

        return descuentos;
    }


    public List<Descuento> buscarDescuentos(Long empleadoId, Long semanaId){
        if(!empleadoRepository.existsById(empleadoId)){
            throw new RuntimeException("Empleado no existe");
        }

        if (!semanaRepository.existsById(semanaId)){
            throw new RuntimeException("Semana no existe");
        }

        return descuentoRepository.buscarDescuentos(empleadoId, semanaId);
    }


    public Descuento obtenerDescuento(Long id){
        return descuentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Descuento no encontrado"));
    }


    public Descuento actualizarDescuento(Long id, Descuento descuentoActualizada){
        Descuento descuento = descuentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Descuento no encontrado"));

        descuento.setEmpleado(descuentoActualizada.getEmpleado());
        descuento.setSemana(descuentoActualizada.getSemana());
        descuento.setMonto(descuentoActualizada.getMonto());
        descuento.setRazon(descuentoActualizada.getRazon());

        return descuentoRepository.save(descuento);
    }


    public void eliminarDescuento(Long id){
        Descuento descuento = descuentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Descuento no encontrado"));

        descuentoRepository.delete(descuento);
    }
}

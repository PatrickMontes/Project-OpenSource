package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.dto.DescuentoDTO;
import pe.edu.upc.projectopensource.entity.Descuento;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.Semana;
import pe.edu.upc.projectopensource.mapper.DescuentoMapper;
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
    private final DescuentoMapper descuentoMapper;


    public DescuentoDTO crearDescuento(DescuentoDTO descuentoDTO){
        Empleado empleado = empleadoRepository.findById(descuentoDTO.getEmpleadoId()).orElseThrow(
                () -> new RuntimeException("No se encontró el empleado"));

        Semana semana = semanaRepository.findById(descuentoDTO.getSemanaId()).orElseThrow(
                () -> new RuntimeException("No se encontró la semana"));

        Descuento descuento = descuentoMapper.toEntity(descuentoDTO);
        descuento.setEmpleado(empleado);
        descuento.setSemana(semana);

        return descuentoMapper.toDto(descuentoRepository.save(descuento));
    }


    public List<DescuentoDTO> obtenerDescuentos(){
        return descuentoRepository.findAll().stream()
                .map(descuentoMapper::toDto)
                .toList();
    }


    public List<DescuentoDTO> buscarDescuentos(Long empleadoId, Long semanaId){
        return descuentoRepository.buscarDescuentos(empleadoId, semanaId).stream()
                .map(descuentoMapper::toDto)
                .toList();
    }


    public DescuentoDTO obtenerDescuento(Long id){
        return descuentoRepository.findById(id)
                .map(descuentoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));
    }


    public DescuentoDTO actualizarDescuento(Long id, DescuentoDTO descuentoDTO){
        Descuento descuento = descuentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Descuento no encontrado"));

        if (descuentoDTO.getEmpleadoId() != null) {
            Empleado empleado = empleadoRepository.findById(descuentoDTO.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("No se encontró el empleado"));
            descuento.setEmpleado(empleado);
        }

        if (descuentoDTO.getSemanaId() != null) {
            Semana semana = semanaRepository.findById(descuentoDTO.getSemanaId())
                    .orElseThrow(() -> new RuntimeException("No se encontró la semana"));
            descuento.setSemana(semana);
        }

        descuento.setMonto(descuentoDTO.getMonto());
        descuento.setRazon(descuentoDTO.getRazon());

        return descuentoMapper.toDto(descuentoRepository.save(descuento));
    }


    public void eliminarDescuento(Long id){
        Descuento descuento = descuentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Descuento no encontrado"));

        descuentoRepository.delete(descuento);
    }
}

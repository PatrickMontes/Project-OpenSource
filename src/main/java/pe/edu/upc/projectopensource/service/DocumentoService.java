package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;
import pe.edu.upc.projectopensource.repository.DocumentoRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {
    private final EmpleadoRepository empleadoRepository;
    private final DocumentoRepository documentoRepository;

    public Documento crearDocumento(Documento documento){
        Empleado empleado = empleadoRepository.findById(documento.getEmpleado().getId()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        documento.setEmpleado(empleado);

        return documentoRepository.save(documento);
    }


    public List<Documento> obtenerDocumentos(){
        return documentoRepository.findAll();
    }


    public List<Documento> buscarDocumentos(Long empleadoId, DocumentoType tipoDocumento){
        return documentoRepository.buscarDocumentos(empleadoId, tipoDocumento);
    }


    public Documento actualizarDocumento(Long id, Documento documentoActualizada){
        Documento documento = documentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Documento no existe"));

        documento.setEmpleado(documentoActualizada.getEmpleado());
        documento.setTipoDocumento(documentoActualizada.getTipoDocumento());
        documento.setUrl(documentoActualizada.getUrl());

        return documentoRepository.save(documento);
    }


    public void eliminarDocumento(Long id){
        Documento documento = documentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Documento no existe"));

        documentoRepository.delete(documento);
    }
}

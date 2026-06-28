package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upc.projectopensource.entity.Documento;
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
        empleadoRepository.findById(documento.getEmpleado().getId()).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        if(documento.getTipoDocumento() == null){
            throw new RuntimeException("El tipo de documento es obligatorio");
        }

        if(documento.getUrl() == null || documento.getUrl().isEmpty()){
            throw new RuntimeException("La URL del documento es obligatoria");
        }

        return documentoRepository.save(documento);
    }


    public List<Documento> obtenerDocumentos(){
        List<Documento> documentos = documentoRepository.findAll();

        if(documentos.isEmpty()){
            throw new RuntimeException("No existen documentos registrados");
        }

        return documentos;
    }


    public List<Documento> buscarDocumentos(Long empleadoId, DocumentoType tipoDocumento){
        if(!empleadoRepository.existsById(empleadoId)){
            throw new RuntimeException("Empleado no existe");
        }

        List<Documento> documentos = documentoRepository.buscarDocumentos(empleadoId, tipoDocumento);

        if(documentos.isEmpty()){
            throw new RuntimeException("No existen documentos para el empleado y tipo de documento especificados");
        }

        return documentos;
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

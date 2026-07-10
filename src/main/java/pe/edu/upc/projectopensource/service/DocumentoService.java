package pe.edu.upc.projectopensource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.projectopensource.dto.DocumentoDTO;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.Empleado;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;
import pe.edu.upc.projectopensource.mapper.DocumentoMapper;
import pe.edu.upc.projectopensource.repository.DocumentoRepository;
import pe.edu.upc.projectopensource.repository.EmpleadoRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentoService {
    private final EmpleadoRepository empleadoRepository;
    private final DocumentoRepository documentoRepository;
    private final CloudinaryService cloudinaryService;
    private final DocumentoMapper documentoMapper;

    public DocumentoDTO crearDocumento(MultipartFile file, Long empleadoId, DocumentoType tipoDocumento) throws IOException {
        Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow(
                () -> new RuntimeException("Empleado no encontrado"));

        Map uploadResult = cloudinaryService.uploadFile(file);
        String url = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        Documento documento = new Documento();
        documento.setEmpleado(empleado);
        documento.setTipoDocumento(tipoDocumento);
        documento.setUrl(url);
        documento.setPublicId(publicId);

        return documentoMapper.toDto(documentoRepository.save(documento));
    }

    public List<DocumentoDTO> obtenerDocumentos(){
        return documentoRepository.findAll().stream()
                .map(documentoMapper::toDto)
                .toList();
    }

    public List<DocumentoDTO> buscarDocumentos(Long empleadoId, DocumentoType tipoDocumento){
        return documentoRepository.buscarDocumentos(empleadoId, tipoDocumento).stream()
                .map(documentoMapper::toDto)
                .toList();
    }

    public DocumentoDTO actualizarDocumento(Long id, MultipartFile file, Long empleadoId, DocumentoType tipoDocumento) throws IOException {
        Documento documento = documentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Documento no existe"));

        if (file != null && !file.isEmpty()) {
            if (documento.getPublicId() != null) {
                cloudinaryService.deleteFile(documento.getPublicId());
            }
            Map uploadResult = cloudinaryService.uploadFile(file);
            documento.setUrl((String) uploadResult.get("url"));
            documento.setPublicId((String) uploadResult.get("public_id"));
        }

        if (empleadoId != null) {
            Empleado empleado = empleadoRepository.findById(empleadoId).orElseThrow(
                    () -> new RuntimeException("Empleado no encontrado"));
            documento.setEmpleado(empleado);
        }

        if (tipoDocumento != null) {
            documento.setTipoDocumento(tipoDocumento);
        }

        return documentoMapper.toDto(documentoRepository.save(documento));
    }

    public void eliminarDocumento(Long id){
        Documento documento = documentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Documento no existe"));

        if (documento.getPublicId() != null) {
            try {
                cloudinaryService.deleteFile(documento.getPublicId());
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar el archivo de Cloudinary", e);
            }
        }

        documentoRepository.delete(documento);
    }
}

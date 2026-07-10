package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.projectopensource.dto.DocumentoDTO;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;
import pe.edu.upc.projectopensource.service.DocumentoService;
import pe.edu.upc.projectopensource.utils.Response;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@Tag(name = "Documentos", description = "Gestión de documentos de los empleados")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @Operation(summary = "Subir un archivo a Cloudinary y crear un documento")
    @PostMapping(path = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<DocumentoDTO>> crearDocumento(
            @RequestParam("file") MultipartFile file,
            @RequestParam("empleadoId") Long empleadoId,
            @RequestParam("tipoDocumento") DocumentoType tipoDocumento
    ) throws IOException {
        DocumentoDTO data = documentoService.crearDocumento(file, empleadoId, tipoDocumento);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<DocumentoDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Documento creado exitosamente")
                        .data(data)
                        .build());
    }

    @GetMapping("/lista")
    public ResponseEntity<Response<List<DocumentoDTO>>> obtenerDocumentos() {
        List<DocumentoDTO> data = documentoService.obtenerDocumentos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<DocumentoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de documentos")
                        .data(data)
                        .build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Response<List<DocumentoDTO>>> buscarDocumentos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) DocumentoType tipoDocumento
    ) {
        List<DocumentoDTO> data = documentoService.buscarDocumentos(empleadoId, tipoDocumento);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<List<DocumentoDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Resultados de búsqueda")
                        .data(data)
                        .build());
    }

    @Operation(summary = "Actualizar un documento (con o sin archivo)")
    @PutMapping(value = "/actualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<DocumentoDTO>> actualizarDocumento(
            @PathVariable Long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "empleadoId", required = false) Long empleadoId,
            @RequestParam(value = "tipoDocumento", required = false) DocumentoType tipoDocumento
    ) throws IOException {
        DocumentoDTO data = documentoService.actualizarDocumento(id, file, empleadoId, tipoDocumento);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.<DocumentoDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Documento actualizado exitosamente")
                        .data(data)
                        .build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.<Void>builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .message("Documento eliminado exitosamente")
                        .build());
    }
}

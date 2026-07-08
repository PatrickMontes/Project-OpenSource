package pe.edu.upc.projectopensource.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;
import pe.edu.upc.projectopensource.service.DocumentoService;

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
    public Documento crearDocumento(
            @RequestParam("file") MultipartFile file,
            @RequestParam("empleadoId") Long empleadoId,
            @RequestParam("tipoDocumento") DocumentoType tipoDocumento
    ) throws IOException {
        return documentoService.crearDocumento(file, empleadoId, tipoDocumento);
    }

    @GetMapping
    public List<Documento> obtenerDocumentos() {
        return documentoService.obtenerDocumentos();
    }

    @GetMapping("/buscar")
    public List<Documento> buscarDocumentos(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) DocumentoType tipoDocumento
    ) {
        return documentoService.buscarDocumentos(empleadoId, tipoDocumento);
    }

    @Operation(summary = "Actualizar un documento (con o sin archivo)")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Documento actualizarDocumento(
            @PathVariable Long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "empleadoId", required = false) Long empleadoId,
            @RequestParam(value = "tipoDocumento", required = false) DocumentoType tipoDocumento
    ) throws IOException {
        return documentoService.actualizarDocumento(id, file, empleadoId, tipoDocumento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
    }
}
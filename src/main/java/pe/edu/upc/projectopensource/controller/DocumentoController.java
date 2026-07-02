package pe.edu.upc.projectopensource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.projectopensource.entity.Documento;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;
import pe.edu.upc.projectopensource.service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping
    public Documento crearDocumento(@RequestBody Documento documento) {
        return documentoService.crearDocumento(documento);
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

    @PutMapping("/{id}")
    public Documento actualizarDocumento(
            @PathVariable Long id,
            @RequestBody Documento documento
    ) {
        return documentoService.actualizarDocumento(id, documento);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
    }
}
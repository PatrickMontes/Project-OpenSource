package pe.edu.upc.projectopensource.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDTO {
    private Long id;

    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "El tipo de documento es obligatorio")
    private DocumentoType tipoDocumento;

    @NotBlank(message = "La ruta o URL del documento es obligatoria")
    private String url;

    private String publicId;
}

package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upc.projectopensource.entity.enums.DocumentoType;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    @NotNull(message = "El empleado es obligatorio")
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    @NotNull(message = "El tipo de documento es obligatorio")
    private DocumentoType tipoDocumento;

    @NotBlank(message = "La ruta o URL del documento es obligatoria")
    private String url;

    @Column(name = "public_id")
    private String publicId;
}
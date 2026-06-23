package pe.edu.upc.projectopensource.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private DocumentoType tipoDocumento;

    private String url;
}
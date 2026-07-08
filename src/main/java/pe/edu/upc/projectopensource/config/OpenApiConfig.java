package pe.edu.upc.projectopensource.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Sistema de Control de Personal y Planillas")
                        .description("API REST para la gestión de empleados, asistencias, turnos, " +
                                "semanas, planillas, pagos y descuentos. " +
                                "Trabajo final del curso IS303 - Open Source Software (UPC).")
                        .version("v1.0")
                        .contact(new Contact().name("Equipo Project-OpenSource")));
    }
}

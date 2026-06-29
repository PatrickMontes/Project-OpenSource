package pe.edu.upc.projectopensource.entity.enums;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PagoEventListener {

    @EventListener
    public void handlePagoCreado(PagoCreadoEvent event) {
        Long planillaId = event.planillaId();

        System.out.println("Planilla a recalcular: " + planillaId);
    }
}
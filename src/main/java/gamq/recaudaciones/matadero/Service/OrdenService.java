package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.OrdenDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdenService {

    OrdenDto findByUuid(String uuid);
    List<OrdenDto> obtenerOrdenes();
    OrdenDto crearOrden(OrdenDto ordenDto);
    OrdenDto actualizarOrden(OrdenDto ordenDto);
    String delete(String uuid, String motivo);
}

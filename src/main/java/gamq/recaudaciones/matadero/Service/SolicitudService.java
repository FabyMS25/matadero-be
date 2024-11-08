package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SolicitudService {

    SolicitudDto findByUuid(String uuid);
    List<SolicitudDto> obtenerSolicitudes();
    SolicitudDto crearSolicitud(SolicitudDto solicitudDto);
    SolicitudDto actualizarSolicitud(SolicitudDto solicitudDto);
    String delete(String uuid);
    String softDelete(String uuid, String motivo);
}

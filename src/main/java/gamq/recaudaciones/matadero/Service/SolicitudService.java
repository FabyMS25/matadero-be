package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface SolicitudService {

    SolicitudDto findByUuid(String uuid);
    SolicitudDto findById(Long id);
    List<SolicitudDto> obtenerSolicitudes();
    List<SolicitudDto> obtenerSolicitudesByFechas(Date fechaIni, Date fechaFin);
    SolicitudDto crearSolicitud(SolicitudDto solicitudDto);
    SolicitudDto actualizarSolicitud(SolicitudDto solicitudDto);
    String delete(String uuid);
    String softDelete(String uuid, String motivo);
}

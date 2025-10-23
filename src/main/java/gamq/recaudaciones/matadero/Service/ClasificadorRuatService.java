package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.ClasificadorRuatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClasificadorRuatService {
    ClasificadorRuatDto findByUuid(String uuid);
    ClasificadorRuatDto findByCodigo(String codigo);
    List<ClasificadorRuatDto> obtenerClasificadores();
    List<ClasificadorRuatDto> obtenerClasificadoresActivos();
//    List<ClasificadorRuatDto> obtenerClasificadoresPorTipoReserva(String tipoReserva);
    ClasificadorRuatDto crearClasificador(ClasificadorRuatDto clasificadorDto);
    ClasificadorRuatDto actualizarClasificador(ClasificadorRuatDto clasificadorDto);
    String eliminarClasificador(String uuid);
    String eliminarClasificadorPermanente(String uuid);
}

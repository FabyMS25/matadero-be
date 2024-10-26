package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContribuyenteService {

    ContribuyenteDto findByUuid(String uuid);
    List<ContribuyenteDto> obtenerContribuyentees();
    ContribuyenteDto crearContribuyente(ContribuyenteDto ContribuyenteDto);
    ContribuyenteDto actualizarContribuyente(ContribuyenteDto ContribuyenteDto);

}

package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {

    CategoriaDto findByUuid(String uuid);
    List<CategoriaDto> obtenerCategorias();
    CategoriaDto crearCategoria(CategoriaDto CategoriaDto);
    CategoriaDto actualizarCategoria(CategoriaDto CategoriaDto);
}

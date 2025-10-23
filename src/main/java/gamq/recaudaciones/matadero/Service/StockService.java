package gamq.recaudaciones.matadero.Service;

import gamq.recaudaciones.matadero.Dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {
    StockDto crearStock(StockDto dto);
    List<StockDto> listarStocks();
    StockDto obtenerPorId(Long id);
    void eliminarStock(Long id);
    StockDto obtenerPorIdSolictud(Long id);
}

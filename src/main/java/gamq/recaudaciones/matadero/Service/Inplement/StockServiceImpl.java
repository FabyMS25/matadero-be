package gamq.recaudaciones.matadero.Service.Inplement;

import gamq.recaudaciones.matadero.Dto.Mapper.StockMapper;
import gamq.recaudaciones.matadero.Dto.StockDto;
import gamq.recaudaciones.matadero.Model.Stock;
import gamq.recaudaciones.matadero.Repository.SolicitudRepository;
import gamq.recaudaciones.matadero.Repository.StockRepository;
import gamq.recaudaciones.matadero.Service.StockService;
import gamq.recaudaciones.matadero.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gamq.recaudaciones.matadero.exception.enums.EntityType.SOLICITUD;
import static gamq.recaudaciones.matadero.exception.enums.EntityType.STOCK;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final SolicitudRepository solicitudRepository;
    private final StockMapper stockMapper;

    @Override
    public StockDto crearStock(StockDto dto) {
        Stock stock = stockMapper.toEntity(dto);

        // Validamos si la solicitud existe
        if (dto.getSolicitudDto() != null && dto.getSolicitudDto().getIdSolicitud() != null) {
            solicitudRepository.findById(dto.getSolicitudDto().getIdSolicitud())
                    .orElseThrow(() -> new RuntimeException("No existe la Solicitud con ID: " + dto.getSolicitudDto().getIdSolicitud()));
        }

        Stock guardado = stockRepository.save(stock);
        return stockMapper.toDto(guardado);
    }

    @Override
    public List<StockDto> listarStocks() {
        return stockRepository.findAll()
                .stream()
                .map(stockMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto obtenerPorId(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe Stock con ID: " + id));
        return stockMapper.toDto(stock);
    }

    @Override
    public void eliminarStock(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe Stock con ID: " + id));
        stockRepository.delete(stock);
    }

    @Override
    public StockDto obtenerPorIdSolictud(Long idSolicitud) {
        // Busca por el ID de solicitud asociado al stock
        Stock stock = stockRepository.stockPorSolicitud(idSolicitud)
                .orElseThrow(() -> new RuntimeException("No existe Stock para la Solicitud ID: " + idSolicitud));
        return stockMapper.toDto(stock);
    }
}

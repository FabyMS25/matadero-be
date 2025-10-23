package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Dto.StockDto;
import gamq.recaudaciones.matadero.Model.Solicitud;
import gamq.recaudaciones.matadero.Model.Stock;
import gamq.recaudaciones.matadero.Repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class StockMapper {

    private final SolicitudRepository solicitudRepository;
    private final SolicitudMapper solicitudMapper;

    public StockDto toDto(Stock s) {
        if (s == null) return null;
        StockDto dto = new StockDto();
        dto.setIdStock(s.getIdStock());
        dto.setUuid(s.getUuid());
        dto.setCantidad(s.getCantidad());
        dto.setCantidadSaldo(s.getCantidadSaldo());
        dto.setSolicitudDto(solicitudMapper.toDto(s.getSolicitud()));
        return dto;
    }

    public Stock toEntity(StockDto dto) {
        if (dto == null) return null;
        Stock s = new Stock();
        s.setIdStock(dto.getIdStock());
        s.setUuid(dto.getUuid());
        s.setCantidad(dto.getCantidad());
        s.setCantidadSaldo(dto.getCantidadSaldo());

        if (dto.getSolicitudDto() != null && dto.getSolicitudDto().getIdSolicitud() != null) {
            solicitudRepository.findById(dto.getSolicitudDto().getIdSolicitud())
                    .ifPresent(s::setSolicitud);
            // si no está presente, se podría crear la entidad vía solicitudMapper.toEntity(...)
        }
        return s;
    }

    public Stock updateEntityFromDto(StockDto dto, Stock entity) {
        if (dto == null || entity == null) return entity;
        entity.setCantidad(dto.getCantidad());
        entity.setCantidadSaldo(dto.getCantidadSaldo());
        // actualización de solicitud la manejamos en servicio
        return entity;
    }
}
package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.ClasificadorRuatDto;
import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Model.ClasificadorRuat;

import java.util.List;
import java.util.stream.Collectors;

public class ClasificadorRuatMapper {

    public static ClasificadorRuatDto toDto(ClasificadorRuat clasificador) {
        if (clasificador == null) return null;

        ClasificadorRuatDto dto = new ClasificadorRuatDto();
        dto.setUuid(clasificador.getUuid());
        dto.setCodigo(clasificador.getCodigo());
        dto.setNombre(clasificador.getNombre());
        dto.setDescripcion(clasificador.getDescripcion());
//        dto.setTipoReserva(clasificador.getTipoReserva());
        dto.setTipoArancel(clasificador.getTipoArancel());
        dto.setActivo(clasificador.getActivo());
        dto.setFechaCreacion(clasificador.getFechaCreacion());
        dto.setEstado(clasificador.isEstado());
        // ✅ MAPEO DE CATEGORÍAS
        if (clasificador.getCategorias() != null) {
            List<CategoriaDto> categoriasDto = clasificador.getCategorias()
                    .stream()
                    .map(CategoriaMapper::toDto)
                    .collect(Collectors.toList());

            dto.setCategorias(categoriasDto);
        }
        return dto;
    }

    public static ClasificadorRuat toEntity(ClasificadorRuatDto dto) {
        if (dto == null) return null;

        ClasificadorRuat clasificador = new ClasificadorRuat();
        clasificador.setUuid(dto.getUuid());
        clasificador.setCodigo(dto.getCodigo());
        clasificador.setNombre(dto.getNombre());
        clasificador.setDescripcion(dto.getDescripcion());
//        clasificador.setTipoReserva(dto.getTipoReserva());
        clasificador.setTipoArancel(dto.getTipoArancel());
        clasificador.setActivo(dto.getActivo());
        clasificador.setFechaCreacion(dto.getFechaCreacion());
        clasificador.setEstado(dto.isEstado());

        return clasificador;
    }

    public static ClasificadorRuat updateEntityFromDto(ClasificadorRuatDto dto, ClasificadorRuat clasificador) {
        if (dto == null || clasificador == null) return clasificador;

        clasificador.setCodigo(dto.getCodigo());
        clasificador.setNombre(dto.getNombre());
        clasificador.setDescripcion(dto.getDescripcion());
        //clasificador.set
        clasificador.setTipoArancel(dto.getTipoArancel());
        clasificador.setActivo(dto.getActivo());


        return clasificador;
    }
}
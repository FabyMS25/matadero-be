package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;

import gamq.recaudaciones.matadero.Model.Solicitud;

public class SolicitudMapper {
    public static SolicitudDto toDto(Solicitud solicitud){
        SolicitudDto  solicitudDto=new SolicitudDto();
        solicitudDto.setUuid(solicitud.getUuid());
        solicitudDto.setFecha(solicitud.getFecha());
        solicitudDto.setCantidad(solicitud.getCantidad());
        solicitudDto.setPrecio(solicitud.getPrecio());
        solicitudDto.setTotal(solicitud.getTotal());
        solicitudDto.setEstado(solicitud.isEstado());
        solicitudDto.setContribuyenteDto(ContribuyenteMapper.toDto(solicitud.getContribuyente()));
        solicitudDto.setCategoriaDto(CategoriaMapper.toDto(solicitud.getCategoria()));

        return solicitudDto
;
    }
    public static Solicitud  toEntity(SolicitudDto  solicitudDto){
        Solicitud solicitud= new Solicitud();
        solicitud.setUuid(solicitudDto.getUuid());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setCantidad(solicitudDto.getCantidad());
        solicitud.setPrecio(solicitudDto.getPrecio());
        solicitud.setTotal(solicitudDto.getTotal());
        solicitud.setEstado(solicitudDto.isEstado());

        return solicitud;
    }
}

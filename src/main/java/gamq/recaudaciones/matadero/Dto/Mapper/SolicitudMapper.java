package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;

import gamq.recaudaciones.matadero.Model.Solicitud;

public class SolicitudMapper {
    public static SolicitudDto toDto(Solicitud solicitud){
        SolicitudDto  solicitudDto=new SolicitudDto();
        solicitudDto.setUuid(solicitud.getUuid());
        solicitudDto.setFecha(solicitud.getFecha());
        solicitudDto.setTipo(solicitud.getTipo());
        solicitudDto.setCantidad(solicitud.getCantidad());
        solicitudDto.setTasa(solicitud.getTasa());
        solicitudDto.setTotal(solicitud.getTotal());
        solicitudDto.setEstado(solicitud.isEstado());
        solicitudDto.setContribuyenteDto(ContribuyenteMapper.toDto(solicitud.getContribuyente()));

        return solicitudDto
;
    }
    public static Solicitud  toEntity(SolicitudDto  solicitudDto){
        Solicitud solicitud= new Solicitud();
        solicitud.setUuid(solicitudDto.getUuid());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setTipo(solicitudDto.getTipo());
        solicitud.setCantidad(solicitudDto.getCantidad());
        solicitud.setTasa(solicitudDto.getTasa());
        solicitud.setTotal(solicitudDto.getTotal());
        solicitud.setEstado(solicitudDto.isEstado());

        return solicitud;
    }
}

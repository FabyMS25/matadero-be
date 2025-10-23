package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Model.Orden;

public class OrdenMapper {

    public static OrdenDto toDto(Orden orden){
        OrdenDto ordenDto=new OrdenDto();
        ordenDto.setIdOrden(orden.getIdOrden());
        ordenDto.setUuid(orden.getUuid());
        ordenDto.setFecha(orden.getFecha());
        ordenDto.setCantidad(orden.getCantidad());
        ordenDto.setPrecio(orden.getPrecio());
        ordenDto.setTasa(orden.getTasa());
        ordenDto.setTotal(orden.getTotal());
        ordenDto.setEstadoPago(orden.getEstadoPago());
        ordenDto.setEstado(ordenDto.isEstado());
        ordenDto.setContribuyenteDto(ContribuyenteMapper.toDto(orden.getContribuyente()));
        ordenDto.setCategoriaDto(CategoriaMapper.toDto(orden.getCategoria()));
        ordenDto.setSolicitudDto(SolicitudMapper.toDto(orden.getSolicitud()));
        ordenDto.setObservacion(orden.getObservacion());
        ordenDto.setMotivo(orden.getMotivo());

        return ordenDto;
    }
    public static Orden toEntity(OrdenDto ordenDto){
        Orden orden= new Orden();
        orden.setIdOrden(ordenDto.getIdOrden());
        orden.setUuid(ordenDto.getUuid());
        orden.setFecha(ordenDto.getFecha());
        orden.setCantidad(ordenDto.getCantidad());
        orden.setPrecio(ordenDto.getPrecio());
        orden.setTasa(ordenDto.getTasa());
        orden.setTotal(ordenDto.getTotal());
        orden.setEstadoPago(ordenDto.getEstadoPago());
        orden.setEstado(ordenDto.isEstado());
        orden.setMotivo(ordenDto.getMotivo());
        orden.setObservacion(ordenDto.getObservacion());

        orden.setCategoria(CategoriaMapper.toEntity(ordenDto.getCategoriaDto()));
        orden.setContribuyente(ContribuyenteMapper.toEntity(ordenDto.getContribuyenteDto()));
        orden.setSolicitud(SolicitudMapper.toEntity(ordenDto.getSolicitudDto()));

        return orden;
    }

}

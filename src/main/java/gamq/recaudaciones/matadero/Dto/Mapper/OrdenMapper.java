package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Model.Orden;

public class OrdenMapper {

    public static OrdenDto toDto(Orden orden){
        OrdenDto ordenDto=new OrdenDto();
        ordenDto.setUuid(orden.getUuid());
        ordenDto.setFecha(orden.getFecha());
        ordenDto.setTipo(orden.getTipo());
        ordenDto.setCantidad(orden.getCantidad());
        ordenDto.setTasa(orden.getTasa());
        ordenDto.setTotal(orden.getTotal());
        ordenDto.setEstadoPago(orden.getEstadoPago());
        ordenDto.setEstado(ordenDto.isEstado());
        ordenDto.setContribuyenteDto(ContribuyenteMapper.toDto(orden.getContribuyente()));

        return ordenDto;
    }
    public static Orden toEntity(OrdenDto ordenDto){
        Orden orden= new Orden();
        orden.setUuid(ordenDto.getUuid());
        orden.setFecha(ordenDto.getFecha());
        orden.setTipo(ordenDto.getTipo());
        orden.setCantidad(ordenDto.getCantidad());
        orden.setTasa(ordenDto.getTasa());
        orden.setTotal(ordenDto.getTotal());
        orden.setEstadoPago(ordenDto.getEstadoPago());
        orden.setEstado(ordenDto.isEstado());

        return orden;
    }

}

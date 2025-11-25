package gamq.recaudaciones.matadero.Dto.Mapper;

import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Dto.SolicitudDto;

import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.Model.Orden;
import gamq.recaudaciones.matadero.Model.Solicitud;
import gamq.recaudaciones.matadero.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitudMapper {
    @Autowired
    private static CategoriaRepository categoriaRepository;

    public static SolicitudDto toDto(Solicitud solicitud){
        SolicitudDto  solicitudDto=new SolicitudDto();
        solicitudDto.setIdSolicitud(solicitud.getIdSolicitud());
        solicitudDto.setUuid(solicitud.getUuid());
        solicitudDto.setFecha(solicitud.getFecha());
        solicitudDto.setCantidad(solicitud.getCantidad());
        solicitudDto.setPrecio(solicitud.getPrecio());
        solicitudDto.setTotal(solicitud.getTotal());
        solicitudDto.setEstado(solicitud.isEstado());
        solicitudDto.setEstadoSolicitud(solicitud.getEstadoSolicitud());
        solicitudDto.setContribuyenteDto(ContribuyenteMapper.toDto(solicitud.getContribuyente()));
        solicitudDto.setCategoriaDto(CategoriaMapper.toDto(solicitud.getCategoria()));
        solicitudDto.setMotivo(solicitud.getMotivo());
        if (solicitud.getOrdenList() != null && !solicitud.getOrdenList().isEmpty()) {
            solicitudDto.setOrdenDtoList(
                    solicitud.getOrdenList()
                            .stream()
                            .map(OrdenMapper::toDto)
                            .collect(Collectors.toList())
            );
        }


        return solicitudDto
;
    }
    public static Solicitud  toEntity(SolicitudDto  solicitudDto){
        Solicitud solicitud= new Solicitud();
        solicitud.setIdSolicitud(solicitudDto.getIdSolicitud());
        solicitud.setUuid(solicitudDto.getUuid());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setCantidad(solicitudDto.getCantidad());
        solicitud.setPrecio(solicitudDto.getPrecio());
        solicitud.setTotal(solicitudDto.getTotal());
        solicitud.setEstado(solicitudDto.isEstado());
        solicitud.setEstadoSolicitud(solicitudDto.getEstadoSolicitud());
        solicitud.setMotivo(solicitudDto.getMotivo());
       /* Categoria categoria = categoriaRepository.findById(solicitudDto.getCategoriaDto().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada en BD con ID: " + solicitudDto.getCategoriaDto().getIdCategoria()));

        solicitud.setCategoria(categoria);

        solicitud.setContribuyente(ContribuyenteMapper.toEntity(solicitudDto.getContribuyenteDto()));*/

        if (solicitudDto.getOrdenDtoList() != null && !solicitudDto.getOrdenDtoList().isEmpty()) {
            List<Orden> ordenes = new ArrayList<>();
            for (OrdenDto ordenDto : solicitudDto.getOrdenDtoList()) {
                Orden orden = OrdenMapper.toEntity(ordenDto);
                // muy importante: establecer relación bidireccional
                orden.setSolicitud(solicitud);
                ordenes.add(orden);
            }
            solicitud.setOrdenList(ordenes);
        }

        return solicitud;
    }
}

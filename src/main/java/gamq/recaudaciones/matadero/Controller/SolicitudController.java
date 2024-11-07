package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.SolicitudService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitud")
public class SolicitudController {

    @Autowired
    SolicitudService SolicitudService;

    @GetMapping("/{uuid}")
    public Response buscarSolicitudPorUuid(@Parameter(description = "Uuid para busqueda de actividad economica")
                                          @PathVariable("uuid") String uuidConstruccion) {
        try{
            SolicitudDto contri = SolicitudService.findByUuid(uuidConstruccion);
            return Response.ok().setPayload(contri);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @GetMapping()
    public Response obtenerSolicituds() {
        try{
            List<SolicitudDto> contris =  SolicitudService.obtenerSolicitudes();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @PostMapping()
    public Response crearSolicitud(@Parameter(description = "Objeto json para crear una Construccion")
                                  @RequestBody SolicitudDto SolicitudDto) {
        try{
            return Response.ok().setPayload(SolicitudService.crearSolicitud(SolicitudDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @PutMapping()
    public Response actualizarSolicitud(@Parameter(description = "Objeto json para actualizar Construccion")
                                       @RequestBody SolicitudDto SolicitudDto) {
        try{
            return Response.ok().setPayload(SolicitudService.actualizarSolicitud(SolicitudDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @DeleteMapping("/{uuid}")
    public Response delete(@Parameter(description = "Uuid para eliminar Solicitud")
                               @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(SolicitudService.delete(uuid));
    }
    @PutMapping("/soft-delete")
    public Response softDelete(
            @RequestParam(name = "uuid", required = true) String uuid,
            @RequestParam(name = "motivo", required = false) String motivo
    ) {
        return Response.ok().setPayload(SolicitudService.softDelete(uuid, motivo));
    }
//    @PutMapping("/soft-delete")
//    public Response softDelete( @RequestBody SolicitudDto SolicitudDto) {
//        try{
//            return Response.ok().setPayload(SolicitudService.actualizarSolicitud(SolicitudDto));
//        } catch (Exception ex) {
//            return  Response.unprocessableEntity().setPayload(ex.getMessage());
//        }
//    }


}

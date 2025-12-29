package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitud")
@Tag(name = "Solicitudes", description = "Servicio de Administracion de Solicitudes")
public class SolicitudController {

    @Autowired
    SolicitudService SolicitudService;

    @Operation(summary = "Buscar una Solicitud por uuid")
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
    @Operation(summary = "Buscar una Solicitud por id")
    @GetMapping("/por/{id}")
    public Response buscarSolicitudPorId(@Parameter(description = "Uuid para busqueda de actividad economica")
                                           @PathVariable("id") Long id) {
        try{
            SolicitudDto contri = SolicitudService.findById(id);
            return Response.ok().setPayload(contri);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Listar todas las Solicitudes")
    @GetMapping()
    public Response obtenerSolicituds() {
        try{
            List<SolicitudDto> contris =  SolicitudService.obtenerSolicitudes();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Listar todas las Solicitudes entre un intervalo de fechas")
    @GetMapping("/fechas")
    public Response buscarSolictudesPorFechaSalida(
            @Parameter(name = "fechaIni",description = "Fecha Salida inicio")
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date fechaIni,
            @Parameter(name = "fechaFin",description = "Fecha Salida fin")
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date fechaFin)
            {
                try{
                    List<SolicitudDto> contris =  SolicitudService.obtenerSolicitudesByFechas(fechaIni,fechaFin);
                    return Response.ok().setPayload(contris);
                } catch(Exception ex) {
                    return Response.notContent().setPayload(ex.getMessage());
                }

    }
    @Operation(summary = "Crea una Solicitud")
    @PostMapping()
    public Response crearSolicitud(@Parameter(description = "Objeto json para crear una Construccion")
                                  @RequestBody SolicitudDto SolicitudDto) {
        try{
            return Response.ok().setPayload(SolicitudService.crearSolicitud(SolicitudDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Actualiza una Solicitud")
    @PutMapping()
    public Response actualizarSolicitud(@Parameter(description = "Objeto json para actualizar Construccion")
                                       @RequestBody SolicitudDto SolicitudDto) {
        try{
            return Response.ok().setPayload(SolicitudService.actualizarSolicitud(SolicitudDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Cambia el estado de la solicitud de uuid")
    @PutMapping("/{uuid}/estado")
    public Response cambiar_Estado(@Parameter(description = "Uuid para cambiar estado  Solicitud")
                                       @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(SolicitudService.Cambiar_estado(uuid));

    }
    @Operation(summary = "Elimina una Solicitude por uuid")
    @DeleteMapping("/{uuid}")
    public Response delete(@Parameter(description = "Uuid para eliminar Solicitud")
                               @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(SolicitudService.delete(uuid));
    }
    @Operation(summary = "Borrado Logico de Una Solicitud")
    @PutMapping("/soft-delete")
    public Response softDelete(
            @RequestParam String uuid,
            @RequestParam String motivo
    ) {
        try {
            return Response.ok().setPayload(SolicitudService.softDelete(uuid, motivo));
        } catch (Exception ex) {
            return Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }


}

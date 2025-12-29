package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
@Tag(name = "Ordenes", description = "Orden de Faeneo a partir de una solicitud ")
public class OrdenController {
    @Autowired
    OrdenService OrdenService;
    @Operation(summary = "Buscar una Orden por uuid")
    @GetMapping("/{uuid}")
    public Response buscarOrdenPorUuid(@Parameter(description = "Uuid para busqueda de actividad economica")
                                           @PathVariable("uuid") String uuidConstruccion) {
        try{
            OrdenDto contri = OrdenService.findByUuid(uuidConstruccion);
            return Response.ok().setPayload(contri);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Listar todas las ordenes")
    @GetMapping()
    public Response obtenerOrdens() {
        try{
            List<OrdenDto> contris =  OrdenService.obtenerOrdenes();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Crear Orden a partir de una solicitud")
    @PostMapping()
    public Response crearOrden(@Parameter(description = "Objeto json para crear una Orden ")
                                   @RequestBody OrdenDto OrdenDto) {
        try{
            return Response.ok().setPayload(OrdenService.crearOrden(OrdenDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Actualizar una  Orden a partir de una solicitud")
    @PutMapping()
    public Response actualizarOrden(@Parameter(description = "Objeto json para actualizar Orden")
                                        @RequestBody OrdenDto OrdenDto) {
        try{
            return Response.ok().setPayload(OrdenService.actualizarOrden(OrdenDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Borrar Orden por uuid")
    @DeleteMapping("/{uuid}")
    public Response delete(@Parameter(description = "Uuid para eliminar Orden")
                           @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(OrdenService.delete(uuid));
    }
    @Operation(summary = "Anula una Orden si es que no esta pagada")
    @PutMapping("/anular")
    public Response anularData(
            @RequestParam(name = "uuid", required = true) String uuid,
            @RequestParam(name = "motivo", required = false) String motivo
    ) {
        return Response.ok().setPayload(OrdenService.anularData(uuid, motivo));
    }
}

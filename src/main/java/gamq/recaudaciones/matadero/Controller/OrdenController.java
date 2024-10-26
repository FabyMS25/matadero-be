package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.OrdenDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.OrdenService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {
    @Autowired
    OrdenService OrdenService;

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
    @GetMapping()
    public Response obtenerOrdens() {
        try{
            List<OrdenDto> contris =  OrdenService.obtenerOrdenes();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @PostMapping()
    public Response crearOrden(@Parameter(description = "Objeto json para crear una Construccion")
                                   @RequestBody OrdenDto OrdenDto) {
        try{
            return Response.ok().setPayload(OrdenService.crearOrden(OrdenDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @PutMapping()
    public Response actualizarOrden(@Parameter(description = "Objeto json para actualizar Construccion")
                                        @RequestBody OrdenDto OrdenDto) {
        try{
            return Response.ok().setPayload(OrdenService.actualizarOrden(OrdenDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
}

package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.ContribuyenteService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contribuyente")
public class ContribuyenteController {

    @Autowired
    ContribuyenteService contribuyenteService;

    @GetMapping("/{uuid}")
    public Response buscarContribuyentePorUuid(@Parameter(description = "Uuid para busqueda de actividad economica")
                                               @PathVariable("uuid") String uuidConstruccion) {
        try{
            ContribuyenteDto contri = contribuyenteService.findByUuid(uuidConstruccion);
            return Response.ok().setPayload(contri);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @GetMapping()
    public Response obtenerContribuyentes() {
        try{
            List<ContribuyenteDto> contris =  contribuyenteService.obtenerContribuyentees();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @PostMapping()
    public Response crearContribuyente(@Parameter(description = "Objeto json para crear una Construccion")
                                       @RequestBody ContribuyenteDto ContribuyenteDto) {
        try{
            return Response.ok().setPayload(contribuyenteService.crearContribuyente(ContribuyenteDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @PutMapping()
    public Response actualizarContribuyente(@Parameter(description = "Objeto json para actualizar Construccion")
                                            @RequestBody ContribuyenteDto ContribuyenteDto) {
        try{
            return Response.ok().setPayload(contribuyenteService.actualizarContribuyente(ContribuyenteDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @DeleteMapping("/{uuid}")
    public Response deleteUser(@Parameter(description = "Uuid para eliminar contribuyente")
                               @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(contribuyenteService.delete(uuid));
    }
}

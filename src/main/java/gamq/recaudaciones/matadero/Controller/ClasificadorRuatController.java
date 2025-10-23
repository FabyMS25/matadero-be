package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.ClasificadorRuatDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.ClasificadorRuatService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clasificadores")
public class ClasificadorRuatController {
    @Autowired
    private ClasificadorRuatService clasificadorRuatService;

    @GetMapping()
    public Response obtenerClasificadores() {
        try{
            List<ClasificadorRuatDto> ClasificadorRuatDtos =  clasificadorRuatService.obtenerClasificadores();
            return Response.ok().setPayload(ClasificadorRuatDtos);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }

    @GetMapping("/{uuid}")
    public Response buscarClasificadorRuatPorUuid( @PathVariable("uuid") String uuidClasificadorRuat) {
        try{
            ClasificadorRuatDto ClasificadorRuatDto = clasificadorRuatService.findByUuid(uuidClasificadorRuat);
            return Response.ok().setPayload(ClasificadorRuatDto);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }

    @PostMapping()
    public Response crearClasificadorRuat(@RequestBody ClasificadorRuatDto ClasificadorRuatDto) {
        try{
            return Response.ok().setPayload(clasificadorRuatService.crearClasificador(ClasificadorRuatDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }

    @PutMapping()
    public Response actualizarClasificadorRuat(@RequestBody ClasificadorRuatDto ClasificadorRuatDto) {
        try{
            return Response.ok().setPayload(clasificadorRuatService.actualizarClasificador(ClasificadorRuatDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @DeleteMapping("/{uuid}")
    public Response deleteCategory(@Parameter(description = "Uuid para eliminar categoria")
                                @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(clasificadorRuatService.eliminarClasificador(uuid));
    }
}

package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.ClasificadorRuatDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.ClasificadorRuatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clasificadores")
@Tag(name = "Clasificadores", description = "Guarda los clasificadores RUAT  de Faeneo")
public class ClasificadorRuatController {
    @Autowired
    private ClasificadorRuatService clasificadorRuatService;
    @Operation(summary = "Lista todos los clasificadores" )
    @GetMapping()
    public Response obtenerClasificadores() {
        try{
            List<ClasificadorRuatDto> ClasificadorRuatDtos =  clasificadorRuatService.obtenerClasificadores();
            return Response.ok().setPayload(ClasificadorRuatDtos);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Busca un clasificador por uuid" )
    @GetMapping("/{uuid}")
    public Response buscarClasificadorRuatPorUuid( @PathVariable("uuid") String uuidClasificadorRuat) {
        try{
            ClasificadorRuatDto ClasificadorRuatDto = clasificadorRuatService.findByUuid(uuidClasificadorRuat);
            return Response.ok().setPayload(ClasificadorRuatDto);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Crear un clasficador de RUAT" )
    @PostMapping()
    public Response crearClasificadorRuat(@RequestBody ClasificadorRuatDto ClasificadorRuatDto) {
        try{
            return Response.ok().setPayload(clasificadorRuatService.crearClasificador(ClasificadorRuatDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Actualiza un clasficador de RUAT" )
    @PutMapping()
    public Response actualizarClasificadorRuat(@RequestBody ClasificadorRuatDto ClasificadorRuatDto) {
        try{
            return Response.ok().setPayload(clasificadorRuatService.actualizarClasificador(ClasificadorRuatDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Actualiza un clasficador de RUAT" )
    @DeleteMapping("/{uuid}")
    public Response deleteCategory(@Parameter(description = "Uuid para eliminar categoria")
                                @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(clasificadorRuatService.eliminarClasificador(uuid));
    }
}

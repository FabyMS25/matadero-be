package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.CategoriaService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
   @Autowired
    CategoriaService categoriaService;
    @GetMapping("/{uuid}")
    public Response buscarCategoriaPorUuid(@Parameter(description = "Uuid para busqueda de actividad economica")
                                               @PathVariable("uuid") String uuidConstruccion) {
        try{
            CategoriaDto contri = categoriaService.findByUuid(uuidConstruccion);
            return Response.ok().setPayload(contri);
        } catch(Exception ex) {
            return Response.notFound().setPayload(ex.getMessage());
        }
    }
    @GetMapping()
    public Response obtenerCategorias() {
        try{
            List<CategoriaDto> contris =  categoriaService.obtenerCategorias();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @PostMapping()
    public Response crearCategoria(@Parameter(description = "Objeto json para crear una Construccion")
                                       @RequestBody CategoriaDto CategoriaDto) {
        try{
            return Response.ok().setPayload(categoriaService.crearCategoria(CategoriaDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @PutMapping()
    public Response actualizarCategoria(@Parameter(description = "Objeto json para actualizar Construccion")
                                            @RequestBody CategoriaDto CategoriaDto) {
        try{
            return Response.ok().setPayload(categoriaService.actualizarCategoria(CategoriaDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @DeleteMapping("/{uuid}")
    public Response delete(@Parameter(description = "Uuid para eliminar categoria")
                               @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(categoriaService.delete(uuid));
    }
}

package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.Dto.CategoriaDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@Tag(name = "Categorias", description = "Categorias de los servicios que Ofrece el Matadero")
public class CategoriaController {
   @Autowired
    CategoriaService categoriaService;
    @Operation(summary = "Buscar una Categoria por uuid")
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
    @Operation(summary = "Listar todas las Categorias")
    @GetMapping()
    public Response obtenerCategorias() {
        try{
            List<CategoriaDto> contris =  categoriaService.obtenerCategorias();
            return Response.ok().setPayload(contris);
        } catch(Exception ex) {
            return Response.notContent().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Crear una Categoria")
    @PostMapping()
    public Response crearCategoria(@Parameter(description = "Objeto json para crear una Construccion")
                                       @RequestBody CategoriaDto CategoriaDto) {
        try{
            return Response.ok().setPayload(categoriaService.crearCategoria(CategoriaDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Actualizar una Categoria")
    @PutMapping()
    public Response actualizarCategoria(@Parameter(description = "Objeto json para actualizar Construccion")
                                            @RequestBody CategoriaDto CategoriaDto) {
        try{
            return Response.ok().setPayload(categoriaService.actualizarCategoria(CategoriaDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Borrar una Categoria por uuid")
    @DeleteMapping("/{uuid}")
    public Response delete(@Parameter(description = "Uuid para eliminar categoria")
                               @PathVariable("uuid") String uuid) {
        return Response.ok().setPayload(categoriaService.delete(uuid));
    }
}

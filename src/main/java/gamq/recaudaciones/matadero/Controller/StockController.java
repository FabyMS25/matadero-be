package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Dto.StockDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Tag(name = "Stock", description = "Stock de las Solicitudes con sus Respectivas Ordenes ")
public class StockController {
    @Autowired
    private StockService stockService;
    @Operation(summary = "Crea un Stock a partir de una Solicitud" )
    @PostMapping
    public Response crearStock(@RequestBody StockDto dto) {
        StockDto creado = stockService.crearStock(dto);
        return Response.ok().setPayload(creado);
    }
    @Operation(summary = "Lista todos los Stocks" )
    @GetMapping
    public Response listarStocks() {
        List<StockDto> lista = stockService.listarStocks();
        return Response.ok().setPayload(lista);
    }
    @Operation(summary = "Busca un Stock a partir de un id de Solicitud" )
   @GetMapping("/{id}")
    public Response obtenerStock(@PathVariable Long id) {
        StockDto dto = stockService.obtenerPorId(id);
        return Response.ok().setPayload(dto);
    }
    @Operation(summary = "Elimina Stock por id" )
    @DeleteMapping("/{id}")
    public Response eliminarStock(@PathVariable Long id) {
        stockService.eliminarStock(id);
        return Response.notContent();
    }
    @Operation(summary = "Actualiza un Stock" )
    @PutMapping()
    public Response actualizarStock(@Parameter(description = "Objeto json para actualizar Stock")
                                        @RequestBody StockDto stockDto) {
        try{
            return Response.ok().setPayload(stockService.actualizarStock(stockDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @Operation(summary = "Obtiene el Saldo actual de una Solicitud" )
    @GetMapping("/saldo/{codigo}")
    public Response obtenerStockPorSolictud(@PathVariable("codigo") Long codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("El ID debe ser numérico");
        }
        StockDto dto = stockService.obtenerPorIdSolictud(codigo);
        return Response.ok().setPayload(dto);
    }

}
package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.SolicitudDto;
import gamq.recaudaciones.matadero.Dto.StockDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")

public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping
    public Response crearStock(@RequestBody StockDto dto) {
        StockDto creado = stockService.crearStock(dto);
        return Response.ok().setPayload(creado);
    }

    @GetMapping
    public Response listarStocks() {
        List<StockDto> lista = stockService.listarStocks();
        return Response.ok().setPayload(lista);
    }

   @GetMapping("/{id}")
    public Response obtenerStock(@PathVariable Long id) {
        StockDto dto = stockService.obtenerPorId(id);
        return Response.ok().setPayload(dto);
    }

    @DeleteMapping("/{id}")
    public Response eliminarStock(@PathVariable Long id) {
        stockService.eliminarStock(id);
        return Response.notContent();
    }
    @PutMapping()
    public Response actualizarStock(@Parameter(description = "Objeto json para actualizar Stock")
                                        @RequestBody StockDto stockDto) {
        try{
            return Response.ok().setPayload(stockService.actualizarStock(stockDto));
        } catch (Exception ex) {
            return  Response.unprocessableEntity().setPayload(ex.getMessage());
        }
    }
    @GetMapping("/saldo/{codigo}")
    public Response obtenerStockPorSolictud(@PathVariable("codigo") Long codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("El ID debe ser numérico");
        }
        StockDto dto = stockService.obtenerPorIdSolictud(codigo);
        return Response.ok().setPayload(dto);
    }

}

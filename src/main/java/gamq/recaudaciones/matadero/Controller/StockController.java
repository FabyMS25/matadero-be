package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.StockDto;
import gamq.recaudaciones.matadero.Dto.response.Response;
import gamq.recaudaciones.matadero.Service.StockService;
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
    /*@GetMapping("/saldo/{id}")
    public ResponseEntity<StockDto> obtenerStockPorSolictud(@PathVariable Long id) {
        StockDto dto = stockService.obtenerPorIdSolictud(id);
        return ResponseEntity.ok(dto);
    }*/
    @GetMapping("/saldo/{codigo}")
    public Response obtenerStockPorSolictud(@PathVariable("codigo") Long codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("El ID debe ser numérico");
        }
        StockDto dto = stockService.obtenerPorIdSolictud(codigo);
        return Response.ok().setPayload(dto);
    }

}

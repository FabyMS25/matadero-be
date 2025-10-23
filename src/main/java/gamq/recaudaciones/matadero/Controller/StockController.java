package gamq.recaudaciones.matadero.Controller;

import gamq.recaudaciones.matadero.Dto.StockDto;
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
    public ResponseEntity<StockDto> crearStock(@RequestBody StockDto dto) {
        StockDto creado = stockService.crearStock(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> listarStocks() {
        List<StockDto> lista = stockService.listarStocks();
        return ResponseEntity.ok(lista);
    }

   @GetMapping("/{id}")
    public ResponseEntity<StockDto> obtenerStock(@PathVariable Long id) {
        StockDto dto = stockService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarStock(@PathVariable Long id) {
        stockService.eliminarStock(id);
        return ResponseEntity.noContent().build();
    }
    /*@GetMapping("/saldo/{id}")
    public ResponseEntity<StockDto> obtenerStockPorSolictud(@PathVariable Long id) {
        StockDto dto = stockService.obtenerPorIdSolictud(id);
        return ResponseEntity.ok(dto);
    }*/
    @GetMapping("/saldo/{codigo}")
    public ResponseEntity<StockDto> obtenerStockPorSolictud(@PathVariable("codigo") Long codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("El ID debe ser numérico");
        }
        StockDto dto = stockService.obtenerPorIdSolictud(codigo);
        return ResponseEntity.ok(dto);
    }

}

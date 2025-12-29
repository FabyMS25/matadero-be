package gamq.recaudaciones.matadero.Controller;


import gamq.recaudaciones.matadero.utils.GeneradorReporte;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/api/reporte")
@Tag(name = "Reportes", description = "Genera los reportes imprimibles del Faeneo")
public class ReportesController {
    @Autowired
    private GeneradorReporte generadorReporte;
    @Operation(summary = "Lsita las Ordenes en un rango de fechas ")
    @RequestMapping(value="/ordenes",method= RequestMethod.GET)
    @ResponseBody
    public void reporteListaReserva (
            @RequestParam(name = "fecha_ini",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_ini,
            @RequestParam(name = "fecha_fin",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_fin,
            @RequestParam(name = "tipo",required = false) String tipo,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();

            parametros.put("fecha_ini",fecha_ini);
            parametros.put("fecha_fin",fecha_fin);
            if(tipo.equals("TODOS")) {
                String cad = "%%";
                parametros.put("tipo", cad);
            }
            else {
                parametros.put("tipo",tipo);
            }
            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "ordenes",
                        "classpath:reportes/ordenes.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    @Operation(summary = "Lista las Solicitudes en un rango de fechas")
    @RequestMapping(value="/solicitudes",method= RequestMethod.GET)
    @ResponseBody
    public void reporteListaSolictudes (
            @RequestParam(name = "fecha_ini",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_ini,
            @RequestParam(name = "fecha_fin",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_fin,
            @RequestParam(name = "tipo",required = false) String tipo,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();
            parametros.put("fecha_ini",fecha_ini);
            parametros.put("fecha_fin",fecha_fin);
            if(tipo.equals("TODOS")) {
                String cad = "%%";
                parametros.put("tipo", cad);
            }
            else {
                parametros.put("tipo",tipo);
            }

            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "solis",
                        "classpath:reportes/solis.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    @Operation(summary = "Lista todo el Stock con sus Ordenes respectivas ")
    @RequestMapping(value="/stock",method= RequestMethod.GET)
    @ResponseBody
    public void reporteStock (
            @RequestParam(name = "fecha_ini",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_ini,
            @RequestParam(name = "fecha_fin",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date fecha_fin,
            @RequestParam(name = "tipo",required = false) String tipo,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();
            parametros.put("fecha_ini",fecha_ini);
            parametros.put("fecha_fin",fecha_fin);
            if(tipo.equals("TODOS")) {
                String cad = "%%";
                parametros.put("tipo", cad);
            }
            else {
                parametros.put("tipo",tipo);
            }

            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "Stock",
                        "classpath:reportes/inventario.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    @Operation(summary = "Busca una orden por uuid")
    @RequestMapping(value="/orden",method= RequestMethod.GET)
    @ResponseBody
    public void reporteorden (
            @RequestParam(name = "uuid",required = false) String uuid,
            @RequestParam(name = "user",required = false) String user,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();

            parametros.put("uuid",uuid);
            parametros.put("user",user);
            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "orden",
                        "classpath:reportes/orden.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    @Operation(summary = "Buscar una Solicitud por uuid")
    @RequestMapping(value="/solicitud",method= RequestMethod.GET)
    @ResponseBody
    public void reporteSolictud (
            @RequestParam(name = "uuid",required = false) String uuid,
            @RequestParam(name = "user",required = false) String user,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();

            parametros.put("uuid",uuid);
            parametros.put("user",user);

            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "solictud",
                        "classpath:reportes/solictud.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
    @Operation(summary = "Ingresos Diarios")
    @RequestMapping(value="/diario",method= RequestMethod.GET)
    @ResponseBody
    public void reporteOrdenesDiarias (
            @RequestParam(name = "dia",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dia,
            @RequestParam(name = "user",required = false) String user,
            @RequestHeader Map<String, String> headers,
            HttpServletResponse response
    ) {
        try {
            //usuario
            //String usuario = headers.getOrDefault("usuario", "Default");
            //ingreso almacen
            HashMap<String, Object> parametros = new HashMap<String,Object>();

            parametros.put("dia",dia);
            parametros.put("user",user);

            //parametros.put("logo64","iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk");

            parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
            try {
                generadorReporte.generarSqlReportePdf(
                        "diario",
                        "classpath:reportes/diario.jrxml",
                        parametros,
                        response
                );
            } catch (SQLException ex) {
                response.setStatus(500);
                throw new RuntimeException(ex);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }


}

package gamq.recaudaciones.matadero.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import gamq.recaudaciones.matadero.Dto.Mapper.SolicitudMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenDto {

    private Long idOrden;
    private String uuid;

    private Date fecha;

    private String tipo;

    private Double cantidad;

    private Double precio;

    private Double total;

    private String tasa;

    private String estadoPago;

    private ContribuyenteDto contribuyenteDto;

    private CategoriaDto categoriaDto;

    private SolicitudDto solicitudDto;

    private String observacion;

    private String motivo;

    private boolean estado;

}

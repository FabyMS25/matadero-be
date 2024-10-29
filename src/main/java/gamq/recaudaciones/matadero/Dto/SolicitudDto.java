package gamq.recaudaciones.matadero.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class SolicitudDto {

    private String uuid;

    private Date fecha;

    private String tipo;

    private Double cantidad;

    private Double tasa;

    private Double total;

    private ContribuyenteDto contribuyenteDto;

    private boolean estado;

}

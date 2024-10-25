package gamq.recaudaciones.matadero.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContribuyenteDto {

    private String uuid;

    private String nombre;

    private String apPaterno;

    private String apMaterno;

    private String nroCarnet;

    private String nroNit;

    private String ciExpedido;

    private String sexo;

    private String direccion;

    private String codigoContribuyente;

    private String tipoDocumento;

    private String estadoCivil;

    private String fechaNac;

    private boolean estado;
}

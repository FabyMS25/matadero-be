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

    private String fullName;

    private String tipoContribuyente;

    private String codigoContribuyente;

    private String tipoDocumento;

    private String numeroDocumento;

    private String expedido;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String genero;

    private String fechaNacimiento;

    private String estadoCivil;

    private String nroNit;

    private String celular;

    private String correo;

    private String direccion;

    private boolean estado;
}

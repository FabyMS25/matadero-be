package gamq.recaudaciones.matadero.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaDto {
    private Long idCategoria;
    private String uuid;

    private String tipo;

    private Double precio;

    private boolean estado;
    // 🔹 SOLO REFERENCIA (opcional)
    private String clasificadorUuid;

    //private ClasificadorRuatDto clasificadorRuatDto;

}

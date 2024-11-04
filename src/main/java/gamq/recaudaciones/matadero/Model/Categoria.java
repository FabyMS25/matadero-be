package gamq.recaudaciones.matadero.Model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "categoria", indexes = @Index(name = "idx_cat_uuid", columnList = "uuid", unique = true))
@SQLDelete(sql = "UPDATE categoria SET estado=true WHERE id_categoria=?")
@Where(clause = "estado = false")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;
    @Column(name = "uuid", updatable = false, unique = true, nullable = false, length = 64)
    private String uuid;
    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;
    @Column(name = "precio", nullable = false)
    private Double precio;
}

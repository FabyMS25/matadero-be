package gamq.recaudaciones.matadero.Model;


import gamq.recaudaciones.matadero.Dto.ContribuyenteDto;
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
@Table(name = "solicitud", indexes = @Index(name = "idx_sol_uuid", columnList = "uuid", unique = true))
@SQLDelete(sql = "UPDATE solicitud SET estado=true WHERE id_solicitud=?")
@Where(clause = "estado = false")

public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solictud")
    private Long idSolictud;
    @Column(name = "uuid", updatable = false, unique = true, nullable = false, length = 64)
    private String uuid;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;
    @Column(name = "cantidad", nullable = false, length = 10)
    private Double cantidad;
    @Column(name = "total", nullable = false, length = 10)
    private Double Total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contribuyente", nullable = true)
    private Contribuyente contribuyente;


}

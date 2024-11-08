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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "orden", indexes = @Index(name = "idx_ord_uuid", columnList = "uuid", unique = true))
@SQLDelete(sql = "UPDATE orden SET estado=true WHERE id_orden=?")
@Where(clause = "estado = false")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solictud")
    private Long idSolictud;
    @Column(name = "uuid", updatable = false, unique = true, nullable = false, length = 64)
    private String uuid;
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "total", nullable = false)
    private Double Total;
    @Column(name = "tasa", nullable = false)
    private String tasa;
    @Column(name = "estado_pago", nullable = false, length = 10)
    private String estadoPago;
    @Column(name = "estado", nullable = false)
    private boolean estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contribuyente", nullable = true)
    private Contribuyente contribuyente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = true)
    private Categoria categoria;

    @Column(name = "observacion", nullable = false, length = 100)
    private String observacion;
    @Column(name = "motivo", nullable = true, length = 100)
    private String motivo;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }
}

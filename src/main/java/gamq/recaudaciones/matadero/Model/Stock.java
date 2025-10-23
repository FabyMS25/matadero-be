package gamq.recaudaciones.matadero.Model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "stock", indexes = @Index(name = "idx_stock_uuid", columnList = "uuid", unique = true))
@SQLDelete(sql = "UPDATE stock SET estado=true WHERE uuid=?")
@Where(clause = "estado = false")

public class Stock {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idStock;
        @Column(name = "uuid", updatable = false, unique = true, nullable = false, length = 64)
        private String uuid;
        @Column(name = "cantidad", nullable = false)
        private Integer cantidad;
        @Column(name = "cantidad_saldo", nullable = false)
        private Integer cantidadSaldo;
        @Column(name = "estado", nullable = false)
        private boolean estado;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_solicitud", nullable = false)
        private Solicitud solicitud;

        @PrePersist
        public void initializeUuid() {
                this.setUuid(UUID.randomUUID().toString());
        }


}

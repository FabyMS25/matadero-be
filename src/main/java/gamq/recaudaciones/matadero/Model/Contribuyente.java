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
@Table(name = "contribuyente", indexes = @Index(name = "idx_cont_uuid", columnList = "uuid", unique = true))
@SQLDelete(sql = "UPDATE contribuyente SET estado=true WHERE id_contribuyente=?")
@Where(clause = "estado = false")
public class Contribuyente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contribuyente")
    private Long idContribuyente;
    @Column(name = "uuid", updatable = false, unique = true, nullable = false, length = 50)
    private String uuid;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "ap_paterno", nullable = false, length = 100)
    private String apPaterno;
    @Column(name = "ap_materno", nullable = false, length = 100)
    private String apMaterno;
    @Column(name = "nro_carnet", nullable = false, length = 15)
    private String nroCarnet;
    @Column(name = "nro_nit", nullable = false, length = 10)
    private String nroNit;
    @Column(name = "ci_expedido", nullable = false, length = 10)
    private String ciExpedido;
    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @Column(name = "codigo_contribuyente", nullable = false,  length = 30)
    private String codigoContribuyente;
    @Column(name = "tipo_documento", nullable = false,  length = 30)
    private String tipoDocumento;
    @Column(name = "estado_civil", nullable = false,  length = 30)
    private String estadoCivil;
    @Column(name = "fecha_nac", nullable = false)
    private String fechaNac;
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }
}

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
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    @Column(name = "tipo_contribuyente", nullable = false,  length = 30)
    private String tipoContribuyente;
    @Column(name = "codigo_contribuyente", nullable = false,  length = 30)
    private String codigoContribuyente;
    @Column(name = "tipo_documento", nullable = false,  length = 30)
    private String tipoDocumento;
    @Column(name = "numero_documento", nullable = false, length = 15)
    private String numeroDocumento;
    @Column(name = "expedido", nullable = false, length = 10)
    private String expedido;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "primer_apellido", nullable = false, length = 100)
    private String primerApellido;
    @Column(name = "segundo_apellido", nullable = true, length = 100)
    private String segundoApellido;
    @Column(name = "genero", nullable = false, length = 10)
    private String genero;
    @Column(name = "fecha_nacimiento", nullable = false)
    private String fechaNacimiento;
    @Column(name = "estado_civil", nullable = false,  length = 30)
    private String estadoCivil;
    @Column(name = "nro_nit", nullable = true, length = 15)
    private String nroNit;
    @Column(name = "celular", nullable = false, length = 15)
    private String celular;
    @Column(name = "correo", nullable = true, length = 100)
    private String correo;
    @Column(name = "direccion", nullable = true, length = 100)
    private String direccion;
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }
}

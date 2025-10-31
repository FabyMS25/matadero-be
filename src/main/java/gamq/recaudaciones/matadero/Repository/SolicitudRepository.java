package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Orden;
import gamq.recaudaciones.matadero.Model.Solicitud;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT a FROM Solicitud a WHERE a.uuid = ?1")
    Optional<Solicitud> findByUuid(String uuid);

    @Query("SELECT s FROM Solicitud s LEFT JOIN FETCH s.ordenList WHERE s.idSolicitud = :id")
    Optional<Solicitud> findByIdWithOrdenes(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Solicitud s LEFT JOIN FETCH s.ordenList")
    List<Solicitud> findAllWithOrdenes();

    @Query("SELECT sa FROM Solicitud sa WHERE sa.fecha BETWEEN ?1 AND ?2 ")
    List<Solicitud> findByFechas(Date fechaIni, Date fechaFin);
}

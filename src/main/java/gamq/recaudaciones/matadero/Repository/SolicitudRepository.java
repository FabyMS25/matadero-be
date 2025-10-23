package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Orden;
import gamq.recaudaciones.matadero.Model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT a FROM Solicitud a WHERE a.uuid = ?1")
    Optional<Solicitud> findByUuid(String uuid);
}

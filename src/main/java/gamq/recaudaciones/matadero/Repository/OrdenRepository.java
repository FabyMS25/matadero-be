package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Contribuyente;
import gamq.recaudaciones.matadero.Model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

    @Query("SELECT a FROM Orden a WHERE a.uuid = ?1")
    Optional<Orden> findByUuid(String uuid);
}

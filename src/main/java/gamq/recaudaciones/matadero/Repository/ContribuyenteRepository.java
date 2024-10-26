package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Contribuyente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContribuyenteRepository extends JpaRepository<Contribuyente, Long> {

    @Query("SELECT a FROM Contribuyente a WHERE a.uuid = ?1")
    Optional<Contribuyente> findByUuid(String uuid);

}

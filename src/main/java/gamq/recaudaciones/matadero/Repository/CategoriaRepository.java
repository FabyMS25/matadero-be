package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Categoria;
import gamq.recaudaciones.matadero.Model.Contribuyente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.management.remote.JMXPrincipal;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT a FROM Categoria a WHERE a.uuid = ?1")
    Optional<Categoria> findByUuid(String uuid);

}

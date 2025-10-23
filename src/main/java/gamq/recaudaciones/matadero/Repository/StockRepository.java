package gamq.recaudaciones.matadero.Repository;

import gamq.recaudaciones.matadero.Model.Solicitud;
import gamq.recaudaciones.matadero.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByUuid(String uuid);

    // Buscar todos los stocks asociados a una solicitud (OneToMany en Solicitud)
    @Query("SELECT a FROM Stock a WHERE a.solicitud.idSolicitud = ?1")
    Optional<Stock> stockPorSolicitud(Long id);
}

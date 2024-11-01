package enzocesarano.progetto01Nov.repositories;

import enzocesarano.progetto01Nov.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {

    boolean existsByEmail(String email);
}

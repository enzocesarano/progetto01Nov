package enzocesarano.progetto01Nov.repositories;

import enzocesarano.progetto01Nov.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
}

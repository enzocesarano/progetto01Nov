package enzocesarano.progetto01Nov.services;

import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.entities.Viaggio;
import enzocesarano.progetto01Nov.exceptions.NotFoundException;
import enzocesarano.progetto01Nov.payloads.PrenotazioneDTO;
import enzocesarano.progetto01Nov.repositories.PrenotazioneRepository;
import enzocesarano.progetto01Nov.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    DipendenteService dipendenteService;

    @Autowired
    ViaggioService viaggioService;

    @Autowired
    ViaggioRepository viaggioRepository;

    public List<Prenotazione> findAll() {
        return this.prenotazioneRepository.findAll();
    }

    public Prenotazione findById(UUID id_prenotazione) {
        return this.prenotazioneRepository.findById(id_prenotazione).orElseThrow(() -> new NotFoundException(id_prenotazione));
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO payload) {
        Viaggio viaggio = this.viaggioService.findById(UUID.fromString(payload.id_viaggio()));
        Dipendente dipendente = this.dipendenteService.findById(UUID.fromString(payload.id_dipendente()));

        // avevo in mente il tipo di controllo che dovevo fare, dato che a me piace complicarmi la vita, ma ad un certo punto mi sono incasinato con le condizioni ç.ç
        // Dal payload, prendo l'id del dipendente e lo salvo (se lo trova!)
        // da "dipendente" prendo la lista di prenotazioni e faccio un anyMach per confrontare le date di andata e ritorno dei viaggi esistenti con quelle
        // del viaggio che gli passo tramite payload.
        boolean prenotazioniSovrapposte = dipendente.getPrenotazioni().stream().anyMatch(prenotazione -> {
            LocalDate dataAndataEsistente = prenotazione.getViaggio().getData_andata();
            LocalDate dataRitornoEsistente = prenotazione.getViaggio().getData_ritorno();
            return (viaggio.getData_andata().isAfter(dataAndataEsistente) &&
                    viaggio.getData_andata().isBefore(dataRitornoEsistente)) ||
                    (viaggio.getData_ritorno().isAfter(dataAndataEsistente) &&
                            viaggio.getData_ritorno().isBefore(dataRitornoEsistente)) ||
                    (viaggio.getData_andata().isBefore(dataRitornoEsistente) &&
                            viaggio.getData_ritorno().isAfter(dataAndataEsistente));
        });
        if (prenotazioniSovrapposte) {
            throw new IllegalStateException("Il dipendente ha già una prenotazione che si sovrappone alle date del viaggio selezionato.");
        }

        Prenotazione newPrenotazione = new Prenotazione(payload.note_dipendente(), viaggio, dipendente);
        return this.prenotazioneRepository.save(newPrenotazione);
    }

    public Prenotazione findByIdAndUpdate(UUID id_prenotazione, PrenotazioneDTO payload) {
        Prenotazione prenotazione = this.findById(id_prenotazione);
        prenotazione.setNote_dipendente(payload.note_dipendente());

        // ho presupposto che una prenotazione come quella creata, potrebbe aver bisogno di una modifica solo delle note da parte del dipendente.
        // Volessi cambiare il viaggio, converrebbe fare una nuova prenotazione e cancellare quella esistente.
        // Lo stesso vale nel caso volessi cambiare il dipendente sulla prenotazione.
        return this.prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(UUID id_prenotazione) {
        Prenotazione prenotazione = this.findById(id_prenotazione);
        this.prenotazioneRepository.delete(prenotazione);
    }

}
package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioneService.findAll();
    }

    @GetMapping("/{id_prenotazione}")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione getPrenotazione(@PathVariable UUID id_prenotazione) {
        return this.prenotazioneService.findById(id_prenotazione);
    }

    @DeleteMapping("/{id_prenotazione}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID id_prenotazione) {
        this.prenotazioneService.deletePrenotazione(id_prenotazione);
    }

    // Ho lasciato la possibilità di creare e modificare una prenotazione SOLO seguendo l'id di un dipendente,

    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione postPrenotazione(@RequestBody PrenotazioneDTO payload) {
        return this.prenotazioneService.savePrenotazione(payload);
    }

    @PutMapping("/{id_prenotazione}")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione putPrenotazione(@PathVariable UUID id_prenotazione, @RequestBody PrenotazioneDTO payload) {
        return this.prenotazioneService.findByIdAndUpdate(id_prenotazione, payload);
    }
    */

}

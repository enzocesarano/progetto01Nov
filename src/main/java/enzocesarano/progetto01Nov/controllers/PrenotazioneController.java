package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.payloads.PrenotazioneDTO;
import enzocesarano.progetto01Nov.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioneService.findAll();
    }

    @GetMapping("/{id_prenotazione}")
    public Prenotazione getPrenotazione(@PathVariable UUID id_prenotazione) {
        return this.prenotazioneService.findById(id_prenotazione);
    }

    @PostMapping
    public Prenotazione postPrenotazione(@RequestBody PrenotazioneDTO payload) {
        return this.prenotazioneService.savePrenotazione(payload);
    }

    @PutMapping("/{id_prenotazione}")
    public Prenotazione putPrenotazione(@PathVariable UUID id_prenotazione, @RequestBody PrenotazioneDTO payload) {
        return this.prenotazioneService.findByIdAndUpdate(id_prenotazione, payload);
    }

    @DeleteMapping("/{id_prenotazione}")
    public void deletePrenotazione(@PathVariable UUID id_prenotazione) {
        this.prenotazioneService.deletePrenotazione(id_prenotazione);
    }
}

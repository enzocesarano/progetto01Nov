package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.payloads.DipendenteDTO;
import enzocesarano.progetto01Nov.payloads.PrenotazioneDTO;
import enzocesarano.progetto01Nov.services.DipendenteService;
import enzocesarano.progetto01Nov.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dipendente> getDipendenti() {
        return this.dipendenteService.findAll();
    }

    @GetMapping("/{id_dipendente}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente getDipendente(@PathVariable UUID id_dipendente) {
        Dipendente dipendente = this.dipendenteService.findById(id_dipendente);
        return dipendente;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente postDipendente(@RequestBody DipendenteDTO dipendente) {
        return this.dipendenteService.saveDipendente(dipendente);
    }

    @PutMapping("/{id_dipendente}")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente putDipendente(@PathVariable UUID id_dipendente, @RequestBody DipendenteDTO dipendente) {
        Dipendente updatedDipendente = this.dipendenteService.findByIdAndUpdate(id_dipendente, dipendente);
        return updatedDipendente;
    }

    @DeleteMapping("/{id_dipendente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable UUID id_dipendente) {

    }

    @PatchMapping("/{id_dipendente}/avatar")
    @ResponseStatus(HttpStatus.OK)
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id_dipendente) {
        return this.dipendenteService.updateAvatar(file, id_dipendente);
    }

    // EndPoint di prenotazioni dato l'id del dipendente.

    @GetMapping("/{id_dipendente}/prenotazioni")
    @ResponseStatus(HttpStatus.OK)
    public List<Prenotazione> getPrenotazioniByDipendente(@PathVariable UUID id_dipendente) {
        List<Prenotazione> prenotazioni = this.dipendenteService.findPrenotazioniByDipendenteId(id_dipendente);
        return prenotazioni;
    }

    // Creazione e modifica di una prenotazione delle prenotazioni SOLO per singolo Dipendente

    @PostMapping("/{id_dipendente}/prenotazioni")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione postPrenotazione(@RequestBody PrenotazioneDTO payload, @PathVariable UUID id_dipendente) {
        return this.prenotazioneService.savePrenotazione(payload, id_dipendente);
    }

    @PutMapping("/{id_dipendente}/prenotazioni/{id_prenotazione}")
    @ResponseStatus(HttpStatus.OK)
    public Prenotazione putPrenotazione(@PathVariable UUID id_dipendente, @RequestBody PrenotazioneDTO payload, @PathVariable UUID id_prenotazione) {
        return this.prenotazioneService.findByIdAndUpdate(id_dipendente, payload, id_prenotazione);
    }
}


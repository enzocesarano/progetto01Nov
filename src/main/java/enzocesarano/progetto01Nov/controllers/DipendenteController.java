package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.payloads.DipendenteDTO;
import enzocesarano.progetto01Nov.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

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

    @GetMapping("/{id_dipendente}/prenotazioni")
    @ResponseStatus(HttpStatus.OK)
    public List<Prenotazione> getPrenotazioniByDipendente(@PathVariable UUID id_dipendente) {
        List<Prenotazione> prenotazioni = this.dipendenteService.findPrenotazioniByDipendenteId(id_dipendente);
        return prenotazioni;
    }
}


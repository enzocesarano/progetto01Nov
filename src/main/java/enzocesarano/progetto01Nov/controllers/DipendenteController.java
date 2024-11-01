package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.payloads.DipendenteDTO;
import enzocesarano.progetto01Nov.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> getDipendenti() {
        return this.dipendenteService.findAll();
    }

    @GetMapping("/{id_dipendente}")
    public Dipendente getDipendente(@PathVariable UUID id_dipendente) {
        return this.dipendenteService.findById(id_dipendente);
    }

    @PostMapping
    public Dipendente postDipendente(@RequestBody DipendenteDTO dipendente) {
        return this.dipendenteService.saveDipendente(dipendente);
    }

    @PutMapping("/{id_dipendente}")
    public Dipendente putDipendente(@PathVariable UUID id_dipendente, @RequestBody DipendenteDTO dipendente) {
        return this.dipendenteService.findByIdAndUpdate(id_dipendente, dipendente);
    }

    @DeleteMapping("/{id_dipendente}")
    public void deleteDipendente(@PathVariable UUID id_dipendente) {
        this.dipendenteService.deleteDipendente(id_dipendente);
    }
}

package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Viaggio;
import enzocesarano.progetto01Nov.payloads.StatoViaggioDTO;
import enzocesarano.progetto01Nov.payloads.ViaggioDTO;
import enzocesarano.progetto01Nov.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Viaggio> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "destinazione") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id_viaggio}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio getViaggio(@PathVariable UUID id_viaggio) {
        return this.viaggioService.findById(id_viaggio);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio postViaggio(@RequestBody ViaggioDTO payload) {
        return this.viaggioService.saveViaggio(payload);
    }

    @PutMapping("/{id_viaggio}")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio putViaggio(@PathVariable UUID id_viaggio, @RequestBody ViaggioDTO payload) {
        return this.viaggioService.findByIdAndUpdate(id_viaggio, payload);
    }

    @DeleteMapping("/{id_viaggio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID id_viaggio) {
        this.viaggioService.deleteViaggio(id_viaggio);
    }

    @PatchMapping("/{id_viaggio}/stato")
    @ResponseStatus(HttpStatus.OK)
    public Viaggio updateViaggioStato(@PathVariable UUID id_viaggio, @RequestBody StatoViaggioDTO payload) {
        return viaggioService.updateViaggioStato(id_viaggio, payload);
    }
}

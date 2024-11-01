package enzocesarano.progetto01Nov.controllers;

import enzocesarano.progetto01Nov.entities.Viaggio;
import enzocesarano.progetto01Nov.payloads.ViaggioDTO;
import enzocesarano.progetto01Nov.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> getViaggi() {
        return this.viaggioService.findAll();
    }

    @GetMapping("/{id_viaggio}")
    public Viaggio getViaggio(@PathVariable UUID id_viaggio) {
        return this.viaggioService.findById(id_viaggio);
    }

    @PostMapping
    public Viaggio postViaggio(@RequestBody ViaggioDTO payload) {
        return this.viaggioService.saveViaggio(payload);
    }

    @PutMapping("/{id_viaggio}")
    public Viaggio putViaggio(@PathVariable UUID id_viaggio, @RequestBody ViaggioDTO payload) {
        return this.viaggioService.findByIdAndUpdate(id_viaggio, payload);
    }

    @DeleteMapping("/{id_viaggio}")
    public void deleteViaggio(@PathVariable UUID id_viaggio) {
        this.viaggioService.deleteViaggio(id_viaggio);
    }
}

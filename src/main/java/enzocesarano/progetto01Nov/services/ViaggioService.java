package enzocesarano.progetto01Nov.services;

import enzocesarano.progetto01Nov.entities.Viaggio;
import enzocesarano.progetto01Nov.exceptions.NotFoundException;
import enzocesarano.progetto01Nov.payloads.ViaggioDTO;
import enzocesarano.progetto01Nov.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> findAll() {
        return this.viaggioRepository.findAll();
    }

    public Viaggio findById(UUID id_viaggio) {
        return this.viaggioRepository.findById(id_viaggio).orElseThrow(() -> new NotFoundException(id_viaggio));
    }

    public Viaggio saveViaggio(ViaggioDTO payload) {
        Viaggio newViaggio = new Viaggio(payload.destinazione(), payload.data_andata(), payload.data_ritorno());
        return this.viaggioRepository.save(newViaggio);
    }

    public Viaggio findByIdAndUpdate(UUID id_viaggio, ViaggioDTO payload) {
        Viaggio viaggio = this.findById(id_viaggio);

        viaggio.setDestinazione(payload.destinazione());
        viaggio.setData_andata(payload.data_andata());
        viaggio.setData_ritorno(payload.data_ritorno());

        return this.viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(UUID id_viaggio) {
        Viaggio viaggio = this.findById(id_viaggio);
        this.viaggioRepository.delete(viaggio);
    }
}

package enzocesarano.progetto01Nov.services;

import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.exceptions.BadRequestException;
import enzocesarano.progetto01Nov.exceptions.NotFoundException;
import enzocesarano.progetto01Nov.payloads.DipendenteDTO;
import enzocesarano.progetto01Nov.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> findAll() {
        return this.dipendenteRepository.findAll();
    }

    public Dipendente findById(UUID id_dipendente) {
        return this.dipendenteRepository.findById(id_dipendente).orElseThrow(() -> new NotFoundException(id_dipendente));
    }

    public Dipendente saveDipendente(DipendenteDTO payload) {
        if (this.dipendenteRepository.existsByEmail(payload.email()))
            throw new BadRequestException("La mail è già in uso");
        Dipendente newDipendente = new Dipendente(payload.nome(), payload.cognome(), payload.email(), payload.data_nascita());
        return this.dipendenteRepository.save(newDipendente);
    }

    public Dipendente findByIdAndUpdate(UUID id_dipendente, DipendenteDTO payload) {
        Dipendente dipendente = this.findById(id_dipendente);
        if (!dipendente.getEmail().equals(payload.email())) {
            if (this.dipendenteRepository.existsByEmail(payload.email()))
                throw new BadRequestException("La mail è già in uso");
        }

        dipendente.setNome(payload.nome());
        dipendente.setCognome(payload.cognome());
        dipendente.setEmail(payload.email());
        dipendente.setData_nascita(payload.data_nascita());

        return this.dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(UUID id_dipendente) {
        Dipendente dipendente = this.findById(id_dipendente);
        this.dipendenteRepository.delete(dipendente);
    }
}

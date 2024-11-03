package enzocesarano.progetto01Nov.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import enzocesarano.progetto01Nov.entities.Dipendente;
import enzocesarano.progetto01Nov.entities.Prenotazione;
import enzocesarano.progetto01Nov.exceptions.BadRequestException;
import enzocesarano.progetto01Nov.exceptions.NotFoundException;
import enzocesarano.progetto01Nov.payloads.DipendenteDTO;
import enzocesarano.progetto01Nov.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID id_dipendente) {
        return this.dipendenteRepository.findById(id_dipendente).orElseThrow(() -> new NotFoundException(id_dipendente));
    }

    public Dipendente saveDipendente(DipendenteDTO payload) {
        if (this.dipendenteRepository.existsByEmail(payload.email()))
            throw new BadRequestException("La mail è già in uso");
        Dipendente newDipendente = new Dipendente(payload.nome(), payload.cognome(), payload.email(), payload.data_nascita());
        newDipendente.setUrl_avatar("https://ui-avatars.com/api/?name=" + payload.nome() + "+" + payload.cognome());
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
        dipendente.setUrl_avatar("https://ui-avatars.com/api/?name=" + payload.nome() + "+" + payload.cognome());

        return this.dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(UUID id_dipendente) {
        Dipendente dipendente = this.findById(id_dipendente);
        this.dipendenteRepository.delete(dipendente);
    }


    public List<Prenotazione> findPrenotazioniByDipendenteId(UUID idDipendente) {
        Dipendente dipendente = this.findById(idDipendente);
        return dipendente.getPrenotazioni();
    }

    public String updateAvatar(MultipartFile file, UUID id_dipendente) {
        Dipendente dipendente = this.findById(id_dipendente);
        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload dell'avatar!");
        }
        dipendente.setUrl_avatar(url);
        this.dipendenteRepository.save(dipendente);
        return url;
    }
}

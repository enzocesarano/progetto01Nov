package enzocesarano.progetto01Nov.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_prenotazione;

    private LocalDate data_prenotazione;
    private String note_dipendente;

    @OneToOne
    @JoinColumn(name = "viaggio")
    @JsonManagedReference
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    @JsonManagedReference
    private Dipendente dipendente;

    public Prenotazione(String note_dipendente, Viaggio viaggio, Dipendente dipendente) {
        this.note_dipendente = note_dipendente;
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.data_prenotazione = LocalDate.now();
    }
}

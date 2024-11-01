package enzocesarano.progetto01Nov.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import enzocesarano.progetto01Nov.entities.Enum.StatoViaggio;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id_viaggio;

    private String destinazione;
    private LocalDate data_andata;
    private LocalDate data_ritorno;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato_viaggio;

    @OneToOne(mappedBy = "viaggio")
    @JsonBackReference
    private Prenotazione prenotazione;


    public Viaggio(String destinazione, LocalDate data_andata, LocalDate data_ritorno) {
        this.destinazione = destinazione;
        this.data_andata = data_andata;
        this.data_ritorno = data_ritorno;
        this.stato_viaggio = StatoViaggio.IN_PROGRAMMA;
    }
}

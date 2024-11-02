package enzocesarano.progetto01Nov.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StatoViaggioDTO(
        @NotBlank(message = "Il campo 'stato_viaggio' non può essere vuoto.")
        @Pattern(regexp = "IN_PROGRAMMA | COMPLETATO",
                message = "Il valore di 'stato_viaggio' deve essere 'IN_PROGRAMMA' o 'COMPLETATO'.")
        String stato_viaggio) {
}
package enzocesarano.progetto01Nov.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        @Size(min = 2, max = 30, message = "La destinazione deve contenere tra 2 e 30 caratteri.")
        String destinazione,

        @NotNull(message = "La data di andata è obbligatoria!")
        @Future(message = "La data di andata non può essere prima di quella attuale.")
        LocalDate data_andata,

        @NotNull(message = "La data di ritorno è obbligatoria!")
        @Future(message = "La data di ritorno non può essere prima di quella attuale.")
        LocalDate data_ritorno) {
}

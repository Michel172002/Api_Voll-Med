package med.voll.api.domain.consult;

import jakarta.validation.constraints.NotNull;

public record ConsultCanceledData(
        @NotNull
        Long idConsult,

        @NotNull
        Reason reason
) {

}

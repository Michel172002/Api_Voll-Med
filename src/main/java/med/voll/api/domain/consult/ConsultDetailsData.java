package med.voll.api.domain.consult;

import java.time.LocalDateTime;

public record ConsultDetailsData(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
    public ConsultDetailsData(Consult consult) {
        this(consult.getId(), consult.getDoctor().getId(), consult.getPatient().getId(), consult.getDate());
    }
}

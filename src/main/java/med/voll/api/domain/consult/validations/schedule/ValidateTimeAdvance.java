package med.voll.api.domain.consult.validations.schedule;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultScheduleData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateTimeAdvance implements ValidatorConsultSchedule{

    public void validate(ConsultScheduleData data){
        var dateConsult = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateConsult).toMinutes();

        if(differenceInMinutes < 30){
            throw new ValidationException("Consulta deve ser agendada com antecendencia de 30 minutos!");
        }
    }
}

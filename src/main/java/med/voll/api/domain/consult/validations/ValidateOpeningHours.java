package med.voll.api.domain.consult.validations;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultScheduleData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateOpeningHours implements ValidatorConsultSchedule{
    public void validate(ConsultScheduleData data){
        var dateConsult = data.date();

        var sunday = dateConsult.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = dateConsult.getHour() < 7;
        var afterClose = dateConsult.getHour() > 18;

        if(sunday || beforeOpening || afterClose){
            throw new ValidationException("Consulta agendada fora do expediente!");
        };
    }
}

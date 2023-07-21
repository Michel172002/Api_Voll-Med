package med.voll.api.domain.consult.validations.schedule;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultRepository;
import med.voll.api.domain.consult.ConsultScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorFree implements ValidatorConsultSchedule{

    @Autowired
    private ConsultRepository consultRepository;

    public void validate(ConsultScheduleData data){
        var doctorHaveAnotherScheduleAtTheTime = consultRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());

        if(doctorHaveAnotherScheduleAtTheTime){
            throw new ValidationException("Medico já possui outra consulta agendada nesse horario!");
        }
    }
}

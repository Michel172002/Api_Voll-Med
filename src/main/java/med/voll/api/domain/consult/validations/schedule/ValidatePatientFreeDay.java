package med.voll.api.domain.consult.validations.schedule;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultRepository;
import med.voll.api.domain.consult.ConsultScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientFreeDay implements ValidatorConsultSchedule{

    @Autowired
    private ConsultRepository consultRepository;

    public void validate(ConsultScheduleData data){
        var fistSchedule = data.date().withHour(7);
        var lastSchedule = data.date().withHour(18);

        var patientHasAnotherConsult = consultRepository.existsByPatientIdAndDateBetween(data.idPatient(), fistSchedule, lastSchedule);
        if(patientHasAnotherConsult){
            throw new ValidationException("Paciente já possui uam consulta agendada nesse dia!");
        }
    }
}

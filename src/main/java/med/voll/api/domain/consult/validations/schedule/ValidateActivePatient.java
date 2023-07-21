package med.voll.api.domain.consult.validations.schedule;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultScheduleData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements ValidatorConsultSchedule {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(ConsultScheduleData data){

        var isActive = patientRepository.findActiveById(data.idPatient());

        if(!isActive){
            throw new ValidationException("Consulta não pode ser agendada com paciente desativado!");
        }
    }
}

package med.voll.api.domain.consult.validations.schedule;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultScheduleData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActiveDoctor implements ValidatorConsultSchedule{

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(ConsultScheduleData data){
        if (data.idDoctor() == null){
            return;
        }

        var isActive = doctorRepository.findActiveById(data.idDoctor());

        if(!isActive){
            throw new ValidationException("Consulta não pode ser agendada com medico desativado!");
        }
    }
}

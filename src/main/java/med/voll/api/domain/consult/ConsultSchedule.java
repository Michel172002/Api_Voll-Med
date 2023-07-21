package med.voll.api.domain.consult;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.validations.cancellation.ValidatorConsultCanceled;
import med.voll.api.domain.consult.validations.schedule.ValidatorConsultSchedule;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultSchedule {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorConsultSchedule> validatorConsultSchedules;

    @Autowired
    private List<ValidatorConsultCanceled> validatorConsultCanceleds;

    public ConsultDetailsData schedule(ConsultScheduleData data){
        if(!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Não existe paciente com o id Informado!");
        }

        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Não existe medico com o id Informado!");
        }

        validatorConsultSchedules.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());

        var doctor = choiceDoctor(data);
        if(doctor == null){
            throw new ValidationException("Não existe medico disponivel!");
        }

        var consult = new Consult(null, doctor, patient, data.date(), null);

        consultRepository.save(consult);
        return new ConsultDetailsData(consult);
    }

    public void cancel(ConsultCanceledData data){
        if(!consultRepository.existsById(data.idConsult())){
            throw new ValidationException("Consulta não encontrada!");
        }

        validatorConsultCanceleds.forEach(v -> v.validate(data));

        var consult = consultRepository.getReferenceById(data.idConsult());
        consult.cancel(data.reason());
    }

    private Doctor choiceDoctor(ConsultScheduleData data) {
        if(data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if(data.specialty() == null){
            throw new ValidationException("Expecialidade é obrigatoria quando medico não é escolhido!");
        }

        return doctorRepository.choiceRandomDoctorFree(data.specialty(), data.date());
    }
}

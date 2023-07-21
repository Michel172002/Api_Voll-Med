package med.voll.api.domain.consult.validations.cancellation;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consult.ConsultCanceledData;
import med.voll.api.domain.consult.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateCancellationTime implements ValidatorConsultCanceled{

    @Autowired
    private ConsultRepository consultRepository;

    public void validate(ConsultCanceledData data){
        var consult = consultRepository.getReferenceById(data.idConsult());
        var now = LocalDateTime.now();
        var differenceHours = Duration.between(now, consult.getDate()).toHours();

        if(differenceHours < 24){
            throw new ValidationException("Consulta não pode ser desagendada(menos de 24 horas de diferença)!");
        }
    }
}

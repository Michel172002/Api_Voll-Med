package med.voll.api.domain.consult.validations.cancellation;

import med.voll.api.domain.consult.ConsultCanceledData;

public interface ValidatorConsultCanceled {
    void validate(ConsultCanceledData data);
}

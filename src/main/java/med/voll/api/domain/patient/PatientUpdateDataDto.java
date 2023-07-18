package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDto;

public record PatientUpdateDataDto(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDto address
) {
}

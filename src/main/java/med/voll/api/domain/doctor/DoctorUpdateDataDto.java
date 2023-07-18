package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDto;

public record DoctorUpdateDataDto(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDto address
) {
}

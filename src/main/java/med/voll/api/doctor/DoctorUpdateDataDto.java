package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressDto;

public record DoctorUpdateDataDto(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDto address
) {
}

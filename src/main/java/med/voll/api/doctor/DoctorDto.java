package med.voll.api.doctor;

import med.voll.api.address.AddressDto;

public record DoctorDto(String name, String email, String crm, Specialty specialty, AddressDto addressDto) {

}

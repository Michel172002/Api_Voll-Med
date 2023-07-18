package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailsDataDto(
        String name,
        String email,
        String phone,
        String crm,
        Specialty specialty,
        Address address,
        Boolean active
) {
    public DoctorDetailsDataDto(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress(), doctor.getActive());
    }
}

package med.voll.api.patient;

import med.voll.api.address.Address;

public record PatientDetailsDataDto(
        String name,
        String email,
        String phone,
        String cpf,
        Address address,
        boolean active
) {
    public PatientDetailsDataDto(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress(), patient.getActive());
    }
}

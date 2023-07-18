package med.voll.api.domain.doctor;

public record DoctorDataPublicDto(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorDataPublicDto(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}

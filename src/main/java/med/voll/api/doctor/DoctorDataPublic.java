package med.voll.api.doctor;

public record DoctorDataPublic(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorDataPublic(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}

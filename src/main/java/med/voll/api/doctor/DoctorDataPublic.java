package med.voll.api.doctor;

public record DoctorDataPublic(
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorDataPublic(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}

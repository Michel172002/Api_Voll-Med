package med.voll.api.domain.patient;

public record PatientDataPublicDto(
        Long id,
        String name,
        String email,
        String cpf
) {
    public PatientDataPublicDto(Patient data){
        this(data.getId(), data.getName(), data.getEmail(), data.getCpf());
    }
}

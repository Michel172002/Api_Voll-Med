package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientDto patientDto){
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phone = patientDto.phone();
        this.cpf = patientDto.cpf();
        this.address = new Address(patientDto.address());
        this.active = true;
    }

    public void UpdateInfo(PatientUpdateDataDto data){
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.address() != null){
            this.address.updateInfo(data.address());
        }
    }

    public void deactivatePatient(){
        this.active = false;
    }
}

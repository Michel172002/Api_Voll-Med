package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.phone = doctorDto.phone();
        this.crm = doctorDto.crm();
        this.specialty = doctorDto.specialty();
        this.address = new Address(doctorDto.address());
        this.active = true;
    }

    public void updateInfo(DoctorUpdateDataDto data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.phone() != null){
            this.name = data.phone();
        }
        if(data.address() != null){
            this.address.updateInfo(data.address());
        }
    }

    public void deactivateDoctor() {
        this.active = false;
    }
}

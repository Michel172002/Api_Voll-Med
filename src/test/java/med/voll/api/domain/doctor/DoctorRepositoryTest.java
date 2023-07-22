package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDto;
import med.voll.api.domain.consult.Consult;
import med.voll.api.domain.consult.ConsultScheduleData;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando o unico medico cadastrado nao esta disponivel na data")
    void choiceRandomDoctorFreeScenario01() {
        //given or arrange
        var nextMondayAt10Hours = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        //then or assert
        var doctor = createDoctor("Erick", "mice@gmail.com", "000000", Specialty.CARDIOLOGIA);
        var patient = createPatient("Ariovaldo", "ari@gmail.com", "0000000000");
        createConsult(doctor, patient, nextMondayAt10Hours);

        //when or act
        var doctorFree = doctorRepository.choiceRandomDoctorFree(Specialty.CARDIOLOGIA, nextMondayAt10Hours);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico que esta disponivel na data")
    void choiceRandomDoctorFreeScenario02() {
        //given or arrange
        var nextMondayAt10Hours = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        //then or assert
        var doctor = createDoctor("Erick", "mice@gmail.com", "000000", Specialty.CARDIOLOGIA);


        //when or act
        var doctorFree = doctorRepository.choiceRandomDoctorFree(Specialty.CARDIOLOGIA, nextMondayAt10Hours);
        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void createConsult(Doctor doctor, Patient patient, LocalDateTime dateTime){
        em.persist(new Consult(null, doctor, patient, dateTime, null));
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty){
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf){
        var patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorDto doctorData(String name, String email, String crm, Specialty specialty){
        return new DoctorDto(
                name,
                email,
                "00000000",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientDto patientData(String name, String email, String cpf){
        return new PatientDto(
                name,
                email,
                "00000000",
                cpf,
                addressData()
        );
    }

    private AddressDto addressData(){
        return new AddressDto(
                "Rua Teste",
                "Bairro",
                "00000000",
                "testCity",
                "sp",
                null,
                null
        );
    }
}
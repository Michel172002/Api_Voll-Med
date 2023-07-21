package med.voll.api.domain.consult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    boolean existsByDoctorIdAndDateAndReasonIsNull(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long aLong, LocalDateTime fistSchedule, LocalDateTime lastSchedule);
}

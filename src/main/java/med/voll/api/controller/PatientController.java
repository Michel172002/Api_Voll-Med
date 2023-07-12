package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void registerPatient(@RequestBody @Valid PatientDto data){
        patientRepository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientDataPublicDto> getPatients(@PageableDefault(size = 10, sort = "name")Pageable pageable){
        return patientRepository.findAll(pageable).map(PatientDataPublicDto::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid PatientUpdateDataDto data){
        var patient = patientRepository.getReferenceById(data.id());
        patient.UpdateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deactivatePatient(@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();
    }
}

package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatient(@RequestBody @Valid PatientDto data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsDataDto(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable(value = "id") Long id){
        var patient = patientRepository.findById(id).get();

        return ResponseEntity.ok(new PatientDetailsDataDto(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDataPublicDto>> getPatients(@PageableDefault(size = 10, sort = "name")Pageable pageable){
        var page = patientRepository.findAll(pageable).map(PatientDataPublicDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid PatientUpdateDataDto data){
        var patient = patientRepository.getReferenceById(data.id());
        patient.UpdateInfo(data);

        return ResponseEntity.ok(new PatientDetailsDataDto(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivatePatient(@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();

        return ResponseEntity.noContent().build();
    }
}

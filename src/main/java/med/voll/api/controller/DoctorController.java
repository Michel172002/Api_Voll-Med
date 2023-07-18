package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody @Valid DoctorDto data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        doctorRepository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDataDto(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity getDoctor(@PathVariable(value = "id") Long id){
        var doctor = doctorRepository.findById(id).get();

        return ResponseEntity.ok(new DoctorDetailsDataDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDataPublicDto>> getDoctors(@PageableDefault(size = 10, sort = "name") Pageable pageable){
        var page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorDataPublicDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateDataDto data){
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateInfo(data);

        return ResponseEntity.ok(new DoctorDetailsDataDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactivateDoctor(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.deactivateDoctor();

        return ResponseEntity.noContent().build();
    }
}

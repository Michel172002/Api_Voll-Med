package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid DoctorDto data){
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorDataPublicDto> getDoctors(@PageableDefault(size = 10, sort = "name") Pageable pageable){
        return doctorRepository.findAll(pageable).map(DoctorDataPublicDto::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DoctorUpdateDataDto data){
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateInfo(data);
    }
}

package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDataPublic;
import med.voll.api.doctor.DoctorDto;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid DoctorDto doctorDto){
        doctorRepository.save(new Doctor(doctorDto));
    }

    @GetMapping
    public Page<DoctorDataPublic> getDoctors(Pageable pageable){
        return doctorRepository.findAll(pageable).map(DoctorDataPublic::new);
    }
}

package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consult.Consult;
import med.voll.api.domain.consult.ConsultCanceledData;
import med.voll.api.domain.consult.ConsultSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import med.voll.api.domain.consult.ConsultScheduleData;

import java.util.List;

@RestController
@RequestMapping("/consult")
public class ConsultController {

    @Autowired
    private ConsultSchedule consultSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ConsultScheduleData data){
        var dto = consultSchedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ConsultCanceledData data){
        consultSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
}

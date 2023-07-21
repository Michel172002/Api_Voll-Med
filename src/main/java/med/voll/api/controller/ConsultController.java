package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consult.ConsultSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import med.voll.api.domain.consult.ConsultScheduleData;
import med.voll.api.domain.consult.ConsultDetailsData;

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
}

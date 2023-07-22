package med.voll.api.controller;

import med.voll.api.domain.consult.ConsultDetailsData;
import med.voll.api.domain.consult.ConsultSchedule;
import med.voll.api.domain.consult.ConsultScheduleData;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultScheduleData> consultScheduleDataJacksonTester;

    @Autowired
    private JacksonTester<ConsultDetailsData> consultDetailsDataJacksonTester;

    @MockBean
    private ConsultSchedule consultSchedule;

    @Test
    @DisplayName("Deveria retornar codigo 400 quando informaçoes forem invalidas")
    @WithMockUser
    void scheduleScenario01() throws Exception {
        var response = mvc.perform(post("/consult"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar codigo 200 quando informaçoes forem validas")
    @WithMockUser
    void scheduleScenario02() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;

        var detailsData = new ConsultDetailsData(null, 5l, 2l, date);
        when(consultSchedule.schedule(any())).thenReturn(detailsData);

        var response = mvc.perform(
                post("/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consultScheduleDataJacksonTester.write(
                                new ConsultScheduleData(2l, 4l, date, specialty)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonExpected = consultDetailsDataJacksonTester.write(detailsData).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}
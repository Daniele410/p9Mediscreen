package com.medipatient.medipatient.curl;

import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.model.dto.PatientDto;
import com.medipatient.medipatient.service.IPatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.CREATED;
@RestController
@RequestMapping
public class PatientCurlController {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(PatientCurlController.class);

    /**
     * Instance of IPatientService
     */
    private final IPatientService patientService;


    public PatientCurlController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> addPatientCurl(@Valid PatientDto patientDto) {
        log.info("save patient curl :{}{}", patientDto.getId(), patientDto.getFamily());
        Patient patient = new Patient(patientDto.getId(), patientDto.getFamily(), patientDto.getGiven(),  LocalDate.parse(patientDto.getDob()), patientDto.getSex()
                .equalsIgnoreCase("F") ? Gender.FEMALE : Gender.MALE, patientDto.getAddress(), patientDto.getPhone());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }
}

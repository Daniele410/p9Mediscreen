package com.medipatient.medipatient.controller;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.IPatientService;
import com.medipatient.medipatient.service.PatientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(PatientController.class);
    private final IPatientService patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }


    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("get all patients");
        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    @GetMapping("/patient")
    public ResponseEntity<Patient> getPatientById(@RequestParam Long id) throws UserNotFoundException {
        log.info("get patient by id :{} ", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    @PutMapping("/patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) throws UserNotFoundException {
        log.info("update patient with id :{} ", patient.getId());
        return new ResponseEntity<>(patientService.updatePatient(patient), OK );
    }

    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        log.info("save patient :{} {}", patient.getFirstName(), patient.getLastName());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Patient> deletePatient(@RequestParam @Valid long id) throws UserNotFoundException {
        log.info("remove patient with id:{}", id);
        return new ResponseEntity<>(patientService.deletePatient(id), OK);
    }

    //update
}

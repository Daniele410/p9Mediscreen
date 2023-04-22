package com.medipatient.medipatient.controller;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.IPatientService;
import com.medipatient.medipatient.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
/**
 * PatientController
 */
@Controller
public class PatientController {
    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    /**
     * Instance of IPatientService
     */
    private final IPatientService patientService;

    @Autowired
    public PatientController(PatientServiceImpl patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }


    /**
     * method to get all patients
     * @return all patients
     */
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("get all patients");
        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    /**
     *
     * @param id
     * @return patient
     * @throws UserNotFoundException
     */
    @GetMapping("/patient")
    public ResponseEntity<Patient> getPatientById( @RequestParam Long id) throws UserNotFoundException {
        log.info("get patient by id :{} ", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientByIdForUi(@PathVariable @RequestParam Long id) throws UserNotFoundException {
        log.info("get patient by id :{} ", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    /**
     * put method to upload patient
     * @param patient
     * @return patient update
     */
    @PutMapping("/patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) throws UserNotFoundException {
        log.info("update patient with id :{} ", patient.getId());
        return new ResponseEntity<>(patientService.updatePatient(patient), OK );
    }

    /**
     * post method to add patient
     * @param  patient
     * @return patient
     */
    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        log.info("save patient :{}{}", patient.getFirstName(), patient.getLastName());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    /**
     * delete method to delete patient by id
     * @param id
     * @return OK
     * @throws UserNotFoundException
     */
    @GetMapping(value = "/patientDelete/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable long id) throws UserNotFoundException {
        log.info("remove patient with id:{}", id);
        return new ResponseEntity<>(patientService.deletePatient(id), OK);
    }

}

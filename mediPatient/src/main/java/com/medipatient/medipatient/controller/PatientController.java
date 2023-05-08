package com.medipatient.medipatient.controller;

import com.medipatient.medipatient.Exception.PatientNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.IPatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
/**
 * PatientController is a REST API controller
 * that handles HTTP requests related to patient information.
 */
@RestController
public class PatientController {
    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    /**
     * Instance of IPatientService
     */
    private final IPatientService patientService;

    /**
     * Constructor for PatientController that injects the PatientServiceImpl dependency.
     * @param patientServiceImpl instance of IPatientService
     */
    @Autowired
    public PatientController(IPatientService patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }


    /**
     * Retrieves a list of all patients.
     * @return a ResponseEntity with a list of Patient objects and an HTTP status code
     */
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("get all patients");
        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    /**
     * Retrieves a patient by ID.
     * @param id the ID of the patient to retrieve
     * @return a ResponseEntity with a Patient object and an HTTP status code
     * @throws PatientNotFoundException if the patient with the given ID is not found
     */
    @GetMapping("/patient")
    public ResponseEntity<Patient> getPatientById( @RequestParam Long id) throws PatientNotFoundException {
        log.info("get patient by id :{} ", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    /**
     * Retrieves a patient by ID for the user interface.
     * @param id the ID of the patient to retrieve
     * @return a ResponseEntity with a Patient object and an HTTP status code
     * @throws PatientNotFoundException if the patient with the given ID is not found
     */
    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientByIdForUi(@PathVariable @RequestParam Long id) throws PatientNotFoundException {
        log.info("get patient by id :{} ", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    /**
     * Put method to upload patient
     * @param patient the patient to update
     * @return  ResponseEntity containing the updated patient
     * @throws PatientNotFoundException if no patient with the given ID is found
     */
    @PutMapping("/patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) throws PatientNotFoundException {
        log.info("update patient with id :{} ", patient.getId());
        return new ResponseEntity<>(patientService.updatePatient(patient), OK );
    }

    /**
     * Add a new patient to the database.
     * @param patient the patient to add
     * @return a ResponseEntity containing the newly added patient
     */
    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        log.info("save patient :{}{}", patient.getFirstName(), patient.getLastName());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    /**
     * Deletes a patient from the database.
     * @param id the ID of the patient to delete
     * @return a ResponseEntity containing the deleted patient
     * @throws PatientNotFoundException if no patient with the given ID is found
     */
    @GetMapping(value = "/patientDelete/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable long id) throws PatientNotFoundException {
        log.info("remove patient with id:{}", id);
        return new ResponseEntity<>(patientService.deletePatient(id), OK);
    }


    /**
     * Retrieves a list of patients from the database that have a matching lastName.
     * @param familyName the last name to search for
     * @return a ResponseEntity containing a list of matching patients
     */
    @GetMapping("/patient/familyName")
    public ResponseEntity<List<Patient>> getPatientByLastName(@RequestParam String familyName) {
        log.info("get patient by lastname :{} request", familyName);
        return new ResponseEntity<>(patientService.findByFirstName(familyName), OK);
    }

}

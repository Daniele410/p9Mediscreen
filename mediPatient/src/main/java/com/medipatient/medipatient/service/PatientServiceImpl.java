package com.medipatient.medipatient.service;

import com.medipatient.medipatient.Exception.PatientNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class PatientServiceImpl implements IPatientService {

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
    public final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * @return all patient present in database
     */
    @Override
    public List<Patient> getAllPatients() {
        log.info("get all patients");
        return patientRepository.findAll();
    }

    /**
     * method to find patient by id
     * @param id
     * @return
     * @throws PatientNotFoundException
     */
    @Override
    public Patient findById(long id) throws PatientNotFoundException {
        log.info("get patient by id: {}", id);
        return patientRepository.findById(id).orElseThrow(()->
                new PatientNotFoundException("id user: {} " + id + " not found!"));
    }

    /**
     * method to save a patient
     * @param patient
     * @return
     */
    @Override
    public Patient savePatient(Patient patient) {
        log.info("save patient: {} {}" + patient.getFirstName(),patient.getLastName());
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws PatientNotFoundException {
        log.info("update patient: {} {}" + patient.getFirstName(), patient.getLastName());
        patientRepository.findById(patient.getId()).orElseThrow(()->
                new PatientNotFoundException("id patient: {} " + patient.getId() + " not found!"));
        return patientRepository.save(patient);
    }

    /**
     * method to delete patient by patient id
     * @param id
     * @return
     * @throws PatientNotFoundException
     */
    @Override
    public Patient deletePatient(long id) throws PatientNotFoundException {
        log.info("patient with: {} " + id , " delete");
        Patient patientToDelete = findById(id);
        patientRepository.delete(patientToDelete);
        return patientToDelete;
    }

    /**
     * method to find patient by firstname
     * @param firstName
     * @return
     */
    @Override
    public List<Patient> findByFirstName(String firstName) {
        log.info("patient with familyName: {} ", firstName);
        return patientRepository.findByFirstName(firstName);

    }

}

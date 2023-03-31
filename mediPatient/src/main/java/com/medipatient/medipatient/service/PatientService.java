package com.medipatient.medipatient.service;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    private final Logger log = LoggerFactory.getLogger(PatientService.class);
    public final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        log.info("get all patients");
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(long id) throws UserNotFoundException {
        log.info("get patient by id: {}", id);
        return patientRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("id user: {} " + id + " not found!"));
    }

    @Override
    public Patient savePatient(Patient patient) {
        log.info("save patient: {} {}" + patient.getFirstName(),patient.getLastName());
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) throws UserNotFoundException {
        log.info("update patient: {} {}" + patient.getFirstName(), patient.getLastName());
        Patient patientUpdate = findById(patient.getId());
        return patientRepository.save(patientUpdate);
    }

    @Override
    public Patient deletePatient(long id) throws UserNotFoundException {
        log.info("patient with: {} " + id , " update");
        Patient patientToDelete = findById(id);
        patientRepository.delete(patientToDelete);
        return patientToDelete;
    }

}

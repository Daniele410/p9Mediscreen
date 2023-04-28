package com.medipatient.medipatient.service;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.model.dto.PatientDto;

import java.util.List;

public interface IPatientService {
    List<Patient> getAllPatients();

    Patient findById(long id) throws UserNotFoundException;

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient) throws UserNotFoundException;

    Patient deletePatient(long id) throws UserNotFoundException;

    List<Patient> findByFirstName(String firstName);
}

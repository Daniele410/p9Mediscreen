package com.medipatient.medipatient.service;

import com.medipatient.medipatient.Exception.PatientNotFoundException;
import com.medipatient.medipatient.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAllPatients();

    Patient findById(long id) throws PatientNotFoundException;

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient) throws PatientNotFoundException;

    Patient deletePatient(long id) throws PatientNotFoundException;

    List<Patient> findByFirstName(String firstName);
}

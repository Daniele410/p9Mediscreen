package com.medipatient.medipatient.curl;

import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.model.dto.PatientDto;
import com.medipatient.medipatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests PatientCurlControllerTest
 */
@ExtendWith(MockitoExtension.class)
class PatientCurlControllerTest {
    @InjectMocks
    PatientCurlController patientCurlController;
    @Mock
    PatientServiceImpl patientService;

    @Test
    void addPatientCurlShouldReturnModifiedModelAndViewCaseMale() {
        //Given
        PatientDto patientDto=new PatientDto();
        patientDto.setId(1L);
        patientDto.setFamily("Mariottide");
        patientDto.setGiven("Nando");
        patientDto.setDob("1954-10-23");
        patientDto.setAddress("my address");
        patientDto.setPhone("000111333222");
        patientDto.setSex(String.valueOf(Gender.MALE));
        Patient patient = new Patient(patientDto.getId(), patientDto.getFamily(), patientDto.getGiven(),  LocalDate.parse(patientDto.getDob()),Gender.MALE, patientDto.getAddress(), patientDto.getPhone());
       when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

       //When
        ResponseEntity<Patient> result = patientCurlController.addPatientCurl(patientDto);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);

    }
    @Test
    void addPatientCurlShouldReturnModifiedModelAndViewCaseFemale() {
        //Given
        PatientDto patientDto=new PatientDto();
        patientDto.setId(1L);
        patientDto.setFamily("Mariottide");
        patientDto.setGiven("Nando");
        patientDto.setDob("1954-10-23");
        patientDto.setAddress("my address");
        patientDto.setPhone("000111333222");
        patientDto.setSex(String.valueOf(Gender.FEMALE));
        Patient patient = new Patient(patientDto.getId(), patientDto.getFamily(), patientDto.getGiven(),  LocalDate.parse(patientDto.getDob()), Gender.FEMALE, patientDto.getAddress(), patientDto.getPhone());
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        //When
        ResponseEntity<Patient> result = patientCurlController.addPatientCurl(patientDto);

        //Then
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);

    }
}
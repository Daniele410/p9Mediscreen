package com.medipatient.medipatient.controller;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.IPatientService;
import com.medipatient.medipatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    PatientServiceImpl patientService;

    @InjectMocks
    private PatientController patientController;



    @Test
    void getAllPatientsShouldReturnModifiedModelAndView() {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient1);
        when(patientService.getAllPatients()).thenReturn(patientsList);

        //When
        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientsList, response.getBody());
    }


    @Test
    void getPatientByIdShouldReturnModifiedModelAndView() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.findById(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.getPatientById(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void getPatientByIdForUiShouldReturnModifiedModelAndView() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.findById(2L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.getPatientByIdForUi(2L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void updatePatientShouldReturnModifiedModelAndView() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.updatePatient(patient)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.updatePatient(patient);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void addPatientShouldReturnModifiedModelAndView() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.savePatient(patient)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.addPatient(patient);

        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void deletePatientShouldReturnModifiedModelAndView() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        Patient patient1 = new Patient(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.deletePatient(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.deletePatient(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    void deletePatientShouldReturnModifiedModelAndView2() throws UserNotFoundException {
        //Given
        List<Patient> patientsList = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Piero");
        patient.setLastName("Brow");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("123123123");
        patientsList.add(patient);
        patientsList.add(patient);
        when(patientService.deletePatient(1L)).thenReturn(patient);

        //When
        ResponseEntity<Patient> response = patientController.deletePatient(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

}
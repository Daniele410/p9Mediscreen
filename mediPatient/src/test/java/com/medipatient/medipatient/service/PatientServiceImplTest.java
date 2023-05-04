package com.medipatient.medipatient.service;

import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepository;

    private Patient patient;
    private Patient patient1;

    @BeforeEach
    void setUp() {
        patient = new Patient(1L, "Mario", "Bros", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        patient1 = new Patient(1L, "Luigi", "Bros", LocalDate.of(1084, 7, 12), Gender.MALE, "St Tata", "123123123123");


    }

    @Test
    void getAllPatients() {
        //Given
        List<Patient> allPatients = List.of(
                new Patient(1L, "Mario", "Bros", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213"),
                new Patient(1L, "Luigi", "Bros", LocalDate.of(1084, 7, 12), Gender.MALE, "St Tata", "123123123123"));
        when(patientRepository.findAll()).thenReturn(allPatients);

        //When
        List<Patient> patientResult = patientService.getAllPatients();

        //Then
        Assertions.assertEquals(patientResult.size(), 2);
    }

    @Test
    void findById() throws UserNotFoundException {
        //Given
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Jimmy");
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        //When
        Optional<Patient> patientFind = Optional.ofNullable(patientService.findById(1L));

        //Then
        Assertions.assertEquals(patientFind.get().getFirstName(), "Jimmy");

    }

    @Test
    void findByIdShouldReturnException() throws UserNotFoundException {
        //Given // When //Then
        assertThrows(UserNotFoundException.class, () -> patientService.findById(3));

    }

    @Test
    void savePatient() {
        //Given
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Jimmy");
        when(patientRepository.save(patient)).thenReturn(patient);

        //When
        Patient result = patientService.savePatient(patient);

        //Then
        Assertions.assertEquals("Jimmy",result.getFirstName());


    }

    @Test
    void updatePatient() throws UserNotFoundException {
        //Given
        List<Patient> patientList = new ArrayList<>();
        patient.setId(1L);
        patientList.add(patient);
        patientList.add(patient1);
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        //When
        patientService.updatePatient(patientList.get(0));

        //Then
        verify(patientRepository, Mockito.times(1)).findById(anyLong());
    }

    @Test
    void updatePatientShouldReturnException() throws UserNotFoundException {
        //Given // When //Then
        assertThrows(UserNotFoundException.class, () -> patientService.updatePatient(patient));
    }

    @Test
    void deletePatient() throws UserNotFoundException {
        //Given
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("NewPatient");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        //When
        patientService.deletePatient(1L);

        //Then
        verify(patientRepository).findById(1L);

    }

    @Test
    void findByFirstName() {
        //Given
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Jimmy");
        patient.setLastName("Bros");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("0678954422");
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientRepository.findByFirstName(patient.getFirstName())).thenReturn(patients);

        //When
        List<Patient> patientFind = patientService.findByFirstName("Jimmy");

        //Then
        Assertions.assertEquals(patientFind.get(0).getFirstName(), "Jimmy");

    }
}
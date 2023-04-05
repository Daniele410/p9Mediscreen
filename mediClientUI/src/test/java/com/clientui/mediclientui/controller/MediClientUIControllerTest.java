package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.proxies.PatientProxy;
import com.medipatient.medipatient.constant.Gender;
import com.medipatient.medipatient.model.Patient;
import io.swagger.models.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediClientUIControllerTest {

    @Mock
    PatientProxy patientProxy;

    @InjectMocks
    private MediClientUIController mediClientUIController;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;


    @Test
    void homePageShouldReturnModifiedModelAndView() {
        //Given //When
        String result = mediClientUIController.homePage();

        //Then
        assertEquals("homePage", result);
    }

    @Test
    void showPatientsShouldReturnModifiedModelAndView() {
        //Given
        List<PatientBean> patientsList = new ArrayList<>();
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        PatientBean patient1 = new PatientBean(2L, "Mario", "Rossi", LocalDate.of(1988, 8, 15), Gender.MALE, "St Tata", "123123123123");
        patientsList.add(patient);
        patientsList.add(patient1);
        when(patientProxy.patientsBeanList()).thenReturn(patientsList);

        //When

        String result = mediClientUIController.showPatients(mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("patients", result);


    }

    @Test
    void showAddPatientFormShouldReturnModifiedModelAndView() {

        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        //When

        String result = mediClientUIController.showAddPatientForm(patient, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("patientForm", result);
    }

    @Test
    void registerPatientShouldReturnModifiedModelAndView_ValidData() {

        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(patientProxy.createPatient(patient)).thenReturn(patient);

        //When

        String result = mediClientUIController.registerPatient(patient, bindingResult);

        //Then
        assertEquals("redirect:/patients?success", result);
        verify(patientProxy, times(1)).createPatient(patient);
        verify(bindingResult, times(1)).hasErrors();
    }

    @Test
    void registerPatientShouldReturnModifiedModelAndView_InvalidData() {

        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        when(bindingResult.hasErrors()).thenReturn(true);

        //When

        String result = mediClientUIController.registerPatient(patient, bindingResult);

        //Then
        assertEquals("redirect:/patientForm?error", result);
        verify(bindingResult, times(1)).hasErrors();
    }

    @Test
    void showUpdateFormShouldReturnModifiedModelAndView() {
        //Given
        Patient patient = new Patient(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        when(patientProxy.getPatient(1)).thenReturn(patient);

        //When

        String result = mediClientUIController.showUpdateForm(1, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("patientUpdateForm", result);
        verify(patientProxy, times(1)).getPatient(1);

    }

    @Test
    void updatePatientFormShouldReturnModifiedModelAndView_ValidData() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(patientProxy.updatePatient(patient)).thenReturn(patient);

        //When

        String result = mediClientUIController.updatePatient(1, patient, bindingResult, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("redirect:/patients?success", result);
        verify(patientProxy, times(1)).updatePatient(patient);
        verify(bindingResult, times(1)).hasErrors();
    }

    @Test
    void updatePatientFormShouldReturnModifiedModelAndView_invalidData() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        when(bindingResult.hasErrors()).thenReturn(true);

        //When

        String result = mediClientUIController.updatePatient(1, patient, bindingResult, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("redirect:/patientUpdate?error", result);
        verify(bindingResult, times(1)).hasErrors();
    }

    @Test
    void deletePatientFormShouldReturnModifiedModelAndView() {
        //Given //When
        String result = mediClientUIController.deletePatient(1L, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("redirect:/patients", result);
        verify(patientProxy, times(1)).deletePatient(1L);
    }
}
package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.NoteBean;
import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.beans.dto.PatientBeanDto;
import com.clientui.mediclientui.constant.Gender;
import com.clientui.mediclientui.constant.RiskLevel;
import com.clientui.mediclientui.proxies.AssessmentProxy;
import com.clientui.mediclientui.proxies.NoteProxy;
import com.clientui.mediclientui.proxies.PatientProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediClientUIControllerTest {

    @Mock
    PatientProxy patientProxy;

    @Mock
    NoteProxy noteProxy;

    @Mock
    AssessmentProxy assessmentProxy;

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
        PatientBean patient = new PatientBean();
        patient.setId(1L);
        patient.setFirstName("Piero");
        patient.setLastName("Brow");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("213213213213");
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
        assertEquals("/patientForm", result);
        verify(bindingResult, times(1)).hasErrors();
    }

    @Test
    void showUpdateFormShouldReturnModifiedModelAndView() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        when(patientProxy.getPatient(1)).thenReturn(patient);

        //When

        String result = mediClientUIController.showUpdateForm(1, mock(org.springframework.ui.Model.class),patient);

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
        PatientBean patient = new PatientBean();
        patient.setId(1L);
        patient.setFirstName("Piero");
        patient.setLastName("Brow");
        patient.setBirthday(LocalDate.of(1084, 9, 10));
        patient.setGender(Gender.MALE);
        patient.setAddress("St Toto");
        patient.setPhone("213213213213");
        when(bindingResult.hasErrors()).thenReturn(true);

        //When

        String result = mediClientUIController.updatePatient(1, patient, bindingResult, mock(org.springframework.ui.Model.class));

        //Then
        assertEquals("redirect:/patientUpdateForm/{id}?error", result);
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

    @Test
    void patientNotes_ShouldReturnModifiedModelAndView() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        List<NoteBean> allNotes = new ArrayList<>();
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        allNotes.add(noteBean1);
        allNotes.add(noteBean2);
        when(patientProxy.getPatient(1L)).thenReturn(patient);
        when(noteProxy.getNoteByPatientId(1L)).thenReturn(allNotes);

        //When

        String result = mediClientUIController.patientNotes(1L,mock(org.springframework.ui.Model.class),patient);

        //Then
        assertEquals("patientNotes", result);


    }

    @Test
    void showAddNoteForm_ShouldReturnModifiedModelAndView() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        List<NoteBean> allNotes = new ArrayList<>();
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        allNotes.add(noteBean1);
        allNotes.add(noteBean2);
        when(patientProxy.getPatient(1L)).thenReturn(patient);


        //When
        String result = mediClientUIController.showAddNoteForm(noteBean1,mock(org.springframework.ui.Model.class),1L);

        //Then
        assertEquals("noteForm", result);


    }

    @Test
    void registerNotePatient_ShouldReturnModifiedModelAndViewBindingResultErrorFalse() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        List<NoteBean> allNotes = new ArrayList<>();
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        allNotes.add(noteBean1);
        allNotes.add(noteBean2);
        when(bindingResult.hasErrors()).thenReturn(false);

        //When
        String result = mediClientUIController.registerNotePatient(noteBean1,bindingResult);

        //Then
        assertEquals("redirect:/patient/note/{patientId}", result);


    }

    @Test
    void registerNotePatient_ShouldReturnModifiedModelAndViewBindingResultErrorTrue() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");

        List<NoteBean> allNotes = new ArrayList<>();
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        allNotes.add(noteBean1);
        allNotes.add(noteBean2);
        when(bindingResult.hasErrors()).thenReturn(true);

        //When
        String result = mediClientUIController.registerNotePatient(noteBean1,bindingResult);

        //Then
        assertEquals("redirect:/noteForm/{patientId}?error", result);

    }

    @Test
    void showUpdateNoteForm_ShouldReturnModifiedModelAndView() {
        //Given
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        when(noteProxy.getNoteById(noteBean1.getId())).thenReturn(noteBean1);


        //When
        String result = mediClientUIController.showUpdateNoteForm(noteBean1.getId(),mock(org.springframework.ui.Model.class),noteBean1);

        //Then
        assertEquals("noteUpdateForm", result);

    }

    @Test
    void updateNote_ShouldReturnModifiedModelAndViewBindingResultErrorFalse() {
        //Given

        List<NoteBean> allNotes = new ArrayList<>();
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        NoteBean noteBean2 = new NoteBean("0987654", 2L, "Bros", LocalDate.of(1986, 1, 18));

        allNotes.add(noteBean1);
        allNotes.add(noteBean2);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(noteProxy.updateNote(noteBean1)).thenReturn(noteBean1);

        //When
        String result = mediClientUIController.updateNote(noteBean1.getId(),noteBean1,bindingResult,model);

        //Then
        assertEquals("redirect:/noteUpdateForm/{id}?success", result);


    }
    @Test
    void updateNote_ShouldReturnModifiedModelAndViewBindingResultErrorTrue() {
        //Given
        NoteBean noteBean1 = new NoteBean();
        noteBean1.setId("1234123");
        noteBean1.setPatientId(1L);
        noteBean1.setMessage("My note");
        noteBean1.setDate(LocalDate.of(1084, 9, 10));

        when(bindingResult.hasErrors()).thenReturn(true);

        //When
        String result = mediClientUIController.updateNote(noteBean1.getId(),noteBean1,bindingResult,model);

        //Then
        assertEquals("redirect:/noteUpdateForm/{id}?error", result);

    }

    @Test
    void deleteNote_ShouldReturnModifiedModelAndViewBindingResultError() {
        //Given
        NoteBean noteBean1 = new NoteBean("1234123", 1L, "Bros", LocalDate.of(1084, 9, 10));
        when(noteProxy.getNoteById(noteBean1.getId())).thenReturn(noteBean1);
        when(noteProxy.deleteNote(noteBean1.getId())).thenReturn(noteBean1);

        //When
        String result = mediClientUIController.deleteNote(noteBean1.getId());

        //Then
        assertEquals("redirect:/patient/note/"+noteBean1.getPatientId()+"?successDelete", result);


    }

    @Test
    void patientAssessment_ShouldReturnModifiedModelAndView() {
        //Given
        PatientBean patient = new PatientBean(1L, "Piero", "Brow", LocalDate.of(1084, 9, 10), Gender.MALE, "St Toto", "213213213213");
        PatientBeanDto assessment = new PatientBeanDto(patient.getFirstName(),patient.getLastName(),38,patient.getGender(),RiskLevel.BORDERLINE);
        when(patientProxy.getPatient(1L)).thenReturn(patient);
        when(assessmentProxy.getRapportAssessmentById(1L)).thenReturn(ResponseEntity.ok(assessment));

        //When
        String result = mediClientUIController.patientAssessment(1L,mock(org.springframework.ui.Model.class),patient);

        //Then
        assertEquals("patientAssessment", result);
        verify(patientProxy).getPatient(patient.getId());
        verify(assessmentProxy).getRapportAssessmentById(patient.getId());

    }



}
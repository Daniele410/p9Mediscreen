package com.mediassessment.mediassessment.service;

import com.mediassessment.mediassessment.beans.NoteBean;
import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.constant.Gender;
import com.mediassessment.mediassessment.constant.RiskLevel;
import com.mediassessment.mediassessment.proxies.NoteProxy;
import com.mediassessment.mediassessment.proxies.PatientProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentImplTest {
    @InjectMocks
    AssessmentImpl assessment;
    @Mock
    NoteProxy noteProxy;
    @Mock
    PatientProxy patientProxy;

    private PatientBean patient;

    private List<NoteBean> allNotes;


    @BeforeEach
    void setUp(){
        patient = new PatientBean(1L, "Mario", "Bros", LocalDate.of(1990, 9, 10), Gender.MALE, "St Toto", "213213213213");
        allNotes = List.of(
                new NoteBean("1234123", 1L, "The is patient have more Hémoglobine", LocalDate.of(1084, 9, 10)),
                new NoteBean("9876543", 1L, "The patient is Fumeur", LocalDate.of(1084, 12, 12)));
    }

    @Test
    void getPatientAge() {
        //Given //When
        long age = assessment.getPatientAge(patient);

        //Then
        assertEquals(age, ChronoUnit.YEARS.between(patient.getBirthday(), LocalDate.now()));
    }

    @Test
    void calculRisks() {
        //Given
        List<NoteBean> notes= new ArrayList<>();

        notes.add(new NoteBean("1234123", 1L, "The is patient have more Hémoglobine", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, "The patient is Fumeur", LocalDate.of(1084, 12, 12)));

        //When
        RiskLevel riskLevel = assessment.calculRisks(patient, notes);

        //Then
        assertEquals(riskLevel,RiskLevel.BORDERLINE);
    }

    @Test
    void calculateTriggerTerms() {
        //Given
        List<NoteBean> notes= new ArrayList<>();

        notes.add(new NoteBean("1234123", 1L, "The is patient have more Hémoglobine", LocalDate.of(1084, 9, 10)));
        notes.add(new NoteBean("9876543", 1L, "The patient is Fumeur", LocalDate.of(1084, 12, 12)));

        //When
        long nbTriggers = assessment.calculateTriggerTerms(notes);

        //Then
        assertEquals(nbTriggers,2);

    }



}
package com.mediassessment.mediassessment.curl;

import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.constant.Gender;
import com.mediassessment.mediassessment.constant.RiskLevel;
import com.mediassessment.mediassessment.service.IAssessmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentCurlControllerTest {

    @InjectMocks
    private AssessmentCurlController assessmentCurlController;

    @Mock
    IAssessmentService assessmentService;

    @Test
    void getCurlReportById_thenReturnString() {

        //Given
        PatientBeanDto patientBean = new PatientBeanDto();
        patientBean.setFirstName("Turiddu");
        patientBean.setLastName("Rossi");
        patientBean.setGender(Gender.MALE);
        patientBean.setAge(60);
        patientBean.setRiskLevel(RiskLevel.BORDERLINE);
        when(assessmentService.getRapportById(1L)).thenReturn(patientBean);

        //When
        ResponseEntity<String> response = assessmentCurlController.getCurlReportById(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient : " + patientBean.getFirstName() +
                " (age " + patientBean.getAge() + ") diabetes assessment is: "
                + patientBean.getRiskLevel().toString(), response.getBody().toString());


    }

    @Test
    void getCurlReportByFamilyName() {
        //Given
        PatientBeanDto patientBean = new PatientBeanDto();
        patientBean.setFirstName("Turiddu");
        patientBean.setLastName("Rossi");
        patientBean.setGender(Gender.MALE);
        patientBean.setAge(60);
        patientBean.setRiskLevel(RiskLevel.NONE);
        when(assessmentService.getRapportByFamilyName("Turiddu")).thenReturn(patientBean);

        //When
        ResponseEntity<String> response = assessmentCurlController.getCurlReportByFamilyName(patientBean.getFirstName());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient : " + patientBean.getFirstName() +
                " (age " + patientBean.getAge() + ") diabetes assessment is: "
                + patientBean.getRiskLevel().toString(), response.getBody().toString());

    }
}
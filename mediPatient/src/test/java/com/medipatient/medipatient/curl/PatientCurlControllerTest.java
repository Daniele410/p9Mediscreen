package com.medipatient.medipatient.curl;

import com.medipatient.medipatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PatientCurlControllerTest {
    @InjectMocks
    PatientCurlController patientCurlController;
    @Mock
    PatientServiceImpl patientService;

    @Test
    void addPatientCurlShouldReturnModifiedModelAndView() {


    }
}
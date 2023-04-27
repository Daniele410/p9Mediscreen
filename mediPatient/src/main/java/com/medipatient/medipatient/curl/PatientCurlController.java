package com.medipatient.medipatient.curl;

import com.medipatient.medipatient.service.IPatientService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class PatientCurlController {


    private final IPatientService patientService;


    public PatientCurlController(IPatientService patientService) {
        this.patientService = patientService;
    }
}

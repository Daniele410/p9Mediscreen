package com.mediassessment.mediassessment.curl;

import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.service.IAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping
public class AssessmentCurlController {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(AssessmentCurlController.class);

    /**
     * Instance of IAssessmentService
     */
   private final IAssessmentService assessmentService;

    public AssessmentCurlController(IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }


    /**
     * Method for curl for test assessment
     * @param patId
     * @return
     */
    @PostMapping(value = "/assess/id")
    public ResponseEntity<String> getCurlReportById(Long patId) {
        log.info(" get report request for patient id {} ",patId);
        PatientBeanDto patientBeanDto = assessmentService.getRapportById(patId);
        String report = "Patient : " + patientBeanDto.getFirstName() +
                " (age " + patientBeanDto.getAge() + ") diabetes assessment is: "
                + patientBeanDto.getRiskLevel().toString();
        return new ResponseEntity<>(report, OK);
    }

    /**
     * Method for curl for test assessment
     * @param familyName
     * @return
     */
    @PostMapping(value = "/assess/familyName")
    public ResponseEntity<String> getCurlReportByFamilyName(@RequestParam String familyName) {
        log.info(" get report request for patient familyName {} ",familyName);
        PatientBeanDto patientBeanDto = assessmentService.getRapportByFamilyName(familyName);
        String report = "Patient : " + patientBeanDto.getFirstName() +
                " (age " + patientBeanDto.getAge() + ") diabetes assessment is: "
                + patientBeanDto.getRiskLevel().toString();
        return new ResponseEntity<>(report, OK);
    }
}

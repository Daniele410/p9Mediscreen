package com.mediassessment.mediassessment.controller;

import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.service.IAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class AssessmentController {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(AssessmentController.class);

    private final IAssessmentService assessmentService;

    public AssessmentController(IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }



    @GetMapping("/assess")
    public ResponseEntity<PatientBeanDto> getRapportAssessmentById (@RequestParam Long id){
        log.info("get rapport by patientId :{} ", id);
        return new ResponseEntity<>(assessmentService.getRapportById((id)), OK);

    }

}

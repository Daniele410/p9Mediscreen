package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.dto.PatientBeanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * This interface represents a FeignClient that
 * provides methods to interact with the mediAssessment service.
 */
@FeignClient(name = "assessment", url = "${feign.mediAssessment.url}")
public interface AssessmentProxy {

    /**
     * Retrieves the assessment of a patient with the given ID.
     * @param id the ID of the patient
     * @return a ResponseEntity containing the assessment of the patient
     */
    @GetMapping("/assess")
    public ResponseEntity<PatientBeanDto> getRapportAssessmentById (@RequestParam Long id);

}

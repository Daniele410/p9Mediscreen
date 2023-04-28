package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.dto.PatientBeanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "assessment", url = "${feign.mediAssessment.url}")
public interface AssessmentProxy {

    @GetMapping("/assess")
    public ResponseEntity<PatientBeanDto> getRapportAssessmentById (@RequestParam Long id);

}

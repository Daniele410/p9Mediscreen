package com.mediassessment.mediassessment.proxies;


import com.mediassessment.mediassessment.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface to connect mediPatient
 */
@FeignClient(name = "patient", url = "${feign.mediPatient.url}")
public interface PatientProxy {

    @GetMapping(value = "/patients")
    List<PatientBean> patientsBeanList();

    @GetMapping( value = "/patient/{id}")
    PatientBean getPatient(@RequestParam @PathVariable("id") long id);


    @GetMapping( value = "/patient/familyName")
    List<PatientBean> getPatientByFirstName(@RequestParam("familyName") String familyName);

}

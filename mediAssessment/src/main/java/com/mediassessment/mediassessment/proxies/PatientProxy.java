package com.mediassessment.mediassessment.proxies;


import com.mediassessment.mediassessment.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Interface for communicating with the mediPatient service via FeignClient.
 */
@FeignClient(name = "patient", url = "${feign.mediPatient.url}")
public interface PatientProxy {

    /**
     * Retrieves a list of all patients from the mediPatient Api.
     * @return A list of PatientBean
     */
    @GetMapping(value = "/patients")
    List<PatientBean> patientsBeanList();

    /**
     * Retrieves a patient with a given ID from the mediPatient Api.
     * @param id The ID of the patient to retrieve.
     * @return A PatientBean object.
     */
    @GetMapping( value = "/patient/{id}")
    PatientBean getPatient(@RequestParam @PathVariable("id") long id);


    /**
     * Retrieves a list of patients with a given familyName from the mediPatient Api.
     * @param familyName The family name of the patients to retrieve.
     * @return A list of PatientBean objects.
     */
    @GetMapping( value = "/patient/familyName")
    List<PatientBean> getPatientByFirstName(@RequestParam("familyName") String familyName);

}

package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping( value = "/patient")
    public PatientBean updatePatient(@RequestBody PatientBean updatedPatient);


    @PostMapping( value = "/patient")
    public PatientBean createPatient(@RequestBody PatientBean patient);


    @GetMapping( value = "/patientDelete/{id}")
    public void deletePatient(@PathVariable long id);




}

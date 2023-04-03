package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.PatientBean;
import com.medipatient.medipatient.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediPatient", url = "localhost:9001")
public interface PatientProxy {

    @GetMapping(value = "/patients")
    List<PatientBean> patientsBeanList();

    @GetMapping( value = "/patient/{id}")
    Patient getPatient(@PathVariable("id") int id);


    @PutMapping( value = "/patient")
    public PatientBean updatePatient(@RequestBody PatientBean updatedPatient);


    @PostMapping( value = "/patient/add")
    public PatientBean createPatient(@RequestBody PatientBean patient);


    @DeleteMapping( value = "/patient/delete/{id}")
    public void deletePatient(@PathVariable Integer id);


}

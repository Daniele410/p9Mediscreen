package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
     * Updates the information of an existing patient.
     * @param updatedPatient The updated PatientBean object.
     * @return The updated PatientBean object.
     */
    @PutMapping( value = "/patient")
    public PatientBean updatePatient(@RequestBody PatientBean updatedPatient);


    /**
     * Creates a new patient.
     * @param patient The PatientBean object to create.
     * @return The created PatientBean object.
     */
    @PostMapping( value = "/patient")
    public PatientBean createPatient(@RequestBody PatientBean patient);


    /**
     * Deletes the Patient associated with the given id.
     * @param id The unique identifier of the patient to delete.
     */
    @GetMapping( value = "/patientDelete/{id}")
    public void deletePatient(@PathVariable long id);




}

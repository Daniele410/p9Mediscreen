package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.proxies.PatientProxy;
import com.medipatient.medipatient.Exception.UserNotFoundException;
import com.medipatient.medipatient.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
public class MediClientUIController {

    private static final Logger logger = LogManager.getLogger("mediClientUIController");

    private final PatientProxy patientProxy;

    public MediClientUIController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }


    @GetMapping("/")
    public String homePage(Model model){
        return "homePage";
    }

    @GetMapping("/patients")
    public String patients(Model model){
        List<PatientBean> patients =  patientProxy.patientsBeanList();
        model.addAttribute("patients", patients);



        return "patients";
    }

    @GetMapping("/patientForm")
    public String showAddPersonForm(PatientBean patientBean,Model model) {
        model.addAttribute("patient", new PatientBean());
        return "patientForm";
    }


    @PostMapping("/patientForm")
    public String registerPatient(@RequestBody @Valid @ModelAttribute("patient") PatientBean patientBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientForm?error";
        }
            patientProxy.createPatient(patientBean);
            logger.info("save patient");
            return "redirect:/patients?success";

    }

    @GetMapping(value = "/patientUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,Model model) {
        logger.debug("get request patient/update/{}", id);
        Patient patient = patientProxy.getPatient(id);

        model.addAttribute("patient", patient);
        return "patientUpdateForm";
    }

    @PostMapping(value = "/patientUpdateForm/{id}")
    public String updatePatient(@PathVariable ("id") long id, @RequestBody @ModelAttribute("patient") PatientBean patientBean, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientUpdate?error";
        }

        patientProxy.updatePatient(patientBean);
        logger.info("update patient");
        return "redirect:/patients?success";

    }



    @GetMapping(value = "/patientDelete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        logger.debug("delete request /delete/{}", id);
        patientProxy.deletePatient(id);
        model.addAttribute("patient", patientProxy.patientsBeanList());
        return "redirect:/patients";
    }



}

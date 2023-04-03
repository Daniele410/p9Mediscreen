package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class mediClientUIController {

    private final PatientProxy patientProxy;

    public mediClientUIController(PatientProxy patientProxy) {
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
    public String showAddPersonForm(@RequestParam(required=false) Integer id, Model model) {
        if (id==null) {
            model.addAttribute("patient", new PatientBean());
        }
        //id is set: update patient
        else {
            model.addAttribute("patient", patientProxy.getPatient(id));
        }
        return "patientForm";
    }

    @PostMapping("/patientForm")
    public String addPatient(@Valid PatientBean patient, BindingResult result, Model model){

        if (result.hasErrors()) {
            return "patientForm";
        }
        patientProxy.createPatient(patient);
        return "patients";
    }

}

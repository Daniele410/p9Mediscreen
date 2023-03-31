package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mediClientUIController {

    private final PatientProxy patientProxy;

    public mediClientUIController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }


//    @GetMapping("/HomePage")
//    public String accueil(Model model){
//        return "HomePage";
//    }

    @GetMapping("/patients")
    public String homePage(Model model){
        List<PatientBean> patients =  patientProxy.patientsBeanList();
        model.addAttribute("patients", patients);



        return "patients";
    }

}

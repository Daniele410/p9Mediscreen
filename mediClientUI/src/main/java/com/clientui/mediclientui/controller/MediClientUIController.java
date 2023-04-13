package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.NoteBean;
import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.proxies.NoteProxy;
import com.clientui.mediclientui.proxies.PatientProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MediClientUIController {

    private static final Logger logger = LogManager.getLogger("mediClientUIController");

    private final PatientProxy patientProxy;

    private final NoteProxy noteProxy;

    public MediClientUIController(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }


    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/patients")
    public String showPatients(Model model) {
        List<PatientBean> patients = patientProxy.patientsBeanList();
        model.addAttribute("patients", patients);

        //add Note

        return "patients";
    }

    @GetMapping("/patientForm")
    public String showAddPatientForm(PatientBean patientBean, Model model) {
        model.addAttribute("patient", new PatientBean());
        logger.info("show add PatientForm");
        return "patientForm";
    }


    @PostMapping("/patientForm")
    public String registerPatient(@RequestBody @ModelAttribute("patient") @Valid PatientBean patientBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientForm?error";
        }
        patientProxy.createPatient(patientBean);
        logger.info("save patient");
        return "redirect:/patients?success";

    }

    @GetMapping(value = "/patientUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        logger.debug("get request patient/update/{}", id);
        PatientBean patient = patientProxy.getPatient(id);
        logger.info("show updatePatient form");
        model.addAttribute("patient", patient);
        return "patientUpdateForm";
    }

    @PostMapping(value = "/patientUpdateForm/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid @RequestBody @ModelAttribute("patient") PatientBean patientBean, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientUpdateForm?error";
        }

        patientProxy.updatePatient(patientBean);
        logger.info("update patient");
        return "redirect:/patients?success";

    }


    @GetMapping(value = "/patientDelete/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        logger.debug("delete request /delete/{}", id);
        patientProxy.deletePatient(id);
        model.addAttribute("patient", patientProxy.patientsBeanList());
        return "redirect:/patients";
    }

    @GetMapping(value = "/patient/note/{id}")
    public String patientNotes(@PathVariable Long id, Model model, @ModelAttribute("patient") PatientBean patientBean) {

        patientBean = patientProxy.getPatient(id);
        model.addAttribute("patients", patientBean);

        List<NoteBean> noteList = noteProxy.getNoteByPatientId(id);
        model.addAttribute("notes", noteList);
        logger.info("show all notes" + patientBean.getId());
        return "patientNotes";


    }

    @GetMapping("/noteForm/{id}")
    public String showAddNoteForm(NoteBean noteBean, Model model, @PathVariable("id") Long id) {
        PatientBean patient = patientProxy.getPatient(id);
        model.addAttribute("patient", patient);

        model.addAttribute("note", new NoteBean());
        logger.info("show add NoteForm");
        return "noteForm";
    }

    @PostMapping(value = "/noteFormAdd/{patientId}")
    public String registerPatient(@PathVariable("patientId") Long patientId, @ModelAttribute("patient") PatientBean patientBean, @Valid @ModelAttribute("note") NoteBean noteBean, BindingResult bindingResult, Model model) {

        patientBean.setId(patientId);
        noteBean.setPatientId(patientBean.getId());
        if (bindingResult.hasErrors()) {
            return "redirect:/noteForm/{patientId}?error";
        }


        noteProxy.addNote(noteBean);
        logger.info("save note");
        return "redirect:/patient/note/{patientId}";

    }

    @GetMapping(value = "/noteUpdateForm/{id}")
    public String showUpdateNoteForm(@PathVariable("id") String id, Model model, NoteBean noteBean) {
        logger.debug("get request /noteUpdateForm/{}", id);
        noteBean = noteProxy.getNoteById(id);
        model.addAttribute("note",noteBean);
        logger.info("show noteUpdateForm");

        return "noteUpdateForm";
    }

    @PostMapping(value = "/noteUpdateForm/{id}")
    public String updateNote(@PathVariable("id") String id,@Valid @RequestBody @ModelAttribute("note") NoteBean noteBean, BindingResult bindingResult, Model model) {
//        NoteBean noteBeanPatientId = noteProxy.getNoteById(id);
//        Long patientId = noteBeanPatientId.getPatientId();
//        model.addAttribute("patient",patientBean);
        if (bindingResult.hasErrors()) {
            return "redirect:/noteUpdateForm/{id}?error";
        }



        noteProxy.updateNote(noteBean);
        logger.info("update note");
        return "redirect:/noteUpdateForm/{id}?success";

    }


}

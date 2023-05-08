package com.clientui.mediclientui.controller;

import com.clientui.mediclientui.beans.NoteBean;
import com.clientui.mediclientui.beans.PatientBean;
import com.clientui.mediclientui.beans.dto.PatientBeanDto;
import com.clientui.mediclientui.proxies.AssessmentProxy;
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

/**
 * This class serves as a controller for the MediClient user interface.
 */
@Controller
public class MediClientUIController {

    private static final Logger logger = LogManager.getLogger("MediClientUIController");

    private final PatientProxy patientProxy;

    private final NoteProxy noteProxy;

    private final AssessmentProxy assessmentProxy;

    /**
     * Constructor for the MediClientUIController class.
     * @param patientProxy a proxy for the mediPatient Service
     * @param noteProxy a proxy for the mediNote service
     * @param assessmentProxy a proxy for the mediAssessment service
     */
    public MediClientUIController(PatientProxy patientProxy, NoteProxy noteProxy, AssessmentProxy assessmentProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
        this.assessmentProxy = assessmentProxy;
    }


    /**
     * Handles the HTTP GET request for the home page.
     * @return representation of the home page view
     */
    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    /**
     * Handles the HTTP GET request for the patients page.
     * @param model the model object to be populated with data
     * @return representation of the patients page view
     */
    @GetMapping("/patients")
    public String showPatients(Model model) {
        List<PatientBean> patients = patientProxy.patientsBeanList();
        model.addAttribute("patients", patients);
        return "patients";
    }

    /**
     * Handles the HTTP GET request for the patient form.
     * @param patientBean
     * @param model
     * @return representation of the patient form view
     */
    @GetMapping("/patientForm")
    public String showAddPatientForm(PatientBean patientBean, Model model) {
        model.addAttribute("patient", new PatientBean());
        logger.info("show add PatientForm");
        return "patientForm";
    }


    /**
     * Handles a POST request to register a new patient.
     * @param patientBean
     * @param bindingResult
     * @return redirect to the list of patients with a success or error message view
     */
    @PostMapping("/patientForm")
    public String registerPatient(@Valid @RequestBody @ModelAttribute("patient")  PatientBean patientBean, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientForm?error";
        }
        patientProxy.createPatient(patientBean);
        logger.info("save patient");
        return "redirect:/patients?success";

    }

    /**
     * Handles a GET request to display the update form for a specific patient.
     * @param id
     * @param model
     * @param patient
     * @return the patientUpdateForm view
     */
    @GetMapping(value = "/patientUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model, PatientBean patient) {
        logger.debug("get request patient/update/{}", id);
        patient = patientProxy.getPatient(id);
        logger.info("show updatePatient form");
        model.addAttribute("patient", patient);
        return "patientUpdateForm";
    }

    /**
     * Handles a POST request to update a specific patient.
     * @param id the ID of the patient to be updated
     * @param patientBean the updated patient
     * @param bindingResult the result of data binding and validation
     * @param model the Spring model
     * @return a redirect to the list of patients with a success or error message
     */
    @PostMapping(value = "/patientUpdateForm/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid @RequestBody @ModelAttribute("patient") PatientBean patientBean, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/patientUpdateForm/{id}?error";
        }

        patientProxy.updatePatient(patientBean);
        logger.info("update patient");
        return "redirect:/patients?success";

    }


    /**
     * Handles a GET request to delete a specific patient.
     * @param id the ID of the patient to be deleted
     * @param model the Spring model
     * @return a redirect to the list of patients view
     */
    @GetMapping(value = "/patientDelete/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        logger.debug("delete request /delete/{}", id);
        patientProxy.deletePatient(id);
        model.addAttribute("patient", patientProxy.patientsBeanList());
        return "redirect:/patients";
    }

    /**
     * Handles a GET request to display the notes for a specific patient.
     * @param id the ID of the patient whose notes will be displayed
     * @param model the Spring model
     * @param patientBean the patient whose notes will be displayed
     * @return the patientNotes view
     */
    @GetMapping(value = "/patient/note/{id}")
    public String patientNotes(@PathVariable Long id, Model model, @ModelAttribute("patient") PatientBean patientBean) {

        patientBean = patientProxy.getPatient(id);
        model.addAttribute("patients", patientBean);

        List<NoteBean> noteList = noteProxy.getNoteByPatientId(id);
        model.addAttribute("notes", noteList);
        logger.info("show all notes" + patientBean.getId());
        return "patientNotes";


    }

    /**
     * Handles a GET request to display the form for adding a new note for a specific patient.
     * @param noteBean the note to be added
     * @param model the Spring model
     * @param id the ID of the patient for whom the note will be added
     * @return the noteForm view
     */
    @GetMapping("/noteForm/{id}")
    public String showAddNoteForm(NoteBean noteBean, Model model, @PathVariable("id") Long id) {
        PatientBean patient = patientProxy.getPatient(id);
        model.addAttribute("patient", patient);
        model.addAttribute("note", new NoteBean());
        logger.info("show add NoteForm");
        return "noteForm";
    }

    /**
     * Handles a POST request to add a new note for a specific patient.
     * @param noteBean the note to be added
     * @param bindingResult the result of data binding and validation
     * @return a redirect to the patient/Note view with a success or error message
     */
    @PostMapping(value = "/noteFormAdd/{patientId}")
    public String registerNotePatient( @Valid @ModelAttribute("note") NoteBean noteBean, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/noteForm/{patientId}?error";
        }

        noteProxy.addNote(noteBean);
        logger.info("save note");
        return "redirect:/patient/note/{patientId}";

    }

    /**
     * Handles a GET request to display the update form for a specific note.
     * @param id the ID of the note to be updated
     * @param model the Spring model
     * @param noteBean the note to be updated
     * @return the noteUpdateForm view
     */
    @GetMapping(value = "/noteUpdateForm/{id}")
    public String showUpdateNoteForm(@PathVariable("id") String id, Model model, NoteBean noteBean) {
        logger.debug("get request /noteUpdateForm/{}", id);
        noteBean = noteProxy.getNoteById(id);
        model.addAttribute("note",noteBean);
        logger.info("show noteUpdateForm");
        return "noteUpdateForm";
    }

    /**
     * Updates a note with the given note ID using the information provided in the request body.
     * @param id the ID of the note to update.
     * @param noteBean  the note to be updated
     * @param bindingResult the result of data binding and validation
     * @param model the Spring model
     * @return
     */
    @PostMapping(value = "/noteUpdateForm/{id}")
    public String updateNote(@PathVariable("id") String id,@Valid @RequestBody @ModelAttribute("note") NoteBean noteBean, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/noteUpdateForm/{id}?error";
        }

        noteProxy.updateNote(noteBean);
        logger.info("update note");
        return "redirect:/noteUpdateForm/{id}?success";

    }

    /**
     * Deletes the note with the given ID.
     * Gets the patient ID associated with the note, deletes the note using the NoteProxy, and redirects to the patient's notes page with a success message.
     * @param id the ID of the note to delete.
     * @return A redirect to the patient/note view page with a success message.
     */
    @GetMapping(value = "/noteDelete/{id}")
    public String deleteNote(@PathVariable ("id") String id) {
        Long patientId = noteProxy.getNoteById(id).getPatientId();
        noteProxy.deleteNote(id);
        logger.info("delete note" + id);
        return "redirect:/patient/note/"+patientId+"?successDelete";

    }

    /**
     * Displays the assessment page for the patient with the given ID.
     * Gets the patient information and assessment result from the PatientProxy and AssessmentProxy respectively,
     * and adds them to the model for display on the page.
     * @param id The ID of the patient to display the assessment for.
     * @param model The Model object for adding attributes.
     * @param patientBean The PatientBean object for the patient being assessed.
     * @return The patientAssessment view.
     */
    @GetMapping(value = "/patient/assess/{id}")
    public String patientAssessment(@PathVariable Long id, Model model, @ModelAttribute("patient") PatientBean patientBean) {

        patientBean = patientProxy.getPatient(id);
        model.addAttribute("patients", patientBean);

        PatientBeanDto assessment = assessmentProxy.getRapportAssessmentById(id).getBody();
        model.addAttribute("assessments", assessment);
        logger.info("show assessment for " + patientBean.getId() + " result " + assessment.getRiskLevel().name());
        return "patientAssessment";


    }



}

package com.mediassessment.mediassessment.service;

import com.mediassessment.mediassessment.beans.NoteBean;
import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;
import com.mediassessment.mediassessment.constant.AssessmentTerminology;
import com.mediassessment.mediassessment.constant.Gender;
import com.mediassessment.mediassessment.constant.RiskLevel;
import com.mediassessment.mediassessment.proxies.NoteProxy;
import com.mediassessment.mediassessment.proxies.PatientProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AssessmentServiceImpl is a class that implements IAssessmentService interface and
 * provides methods to calculate the risk level of a patient based on their notes and demographic information.
 */
@Service
public class AssessmentServiceImpl implements IAssessmentService {

    /**
     * SLF4J Logger instance.
     */
    private static final Logger logger = LogManager.getLogger("AssessmentImpl");

    /**
     * NoteProxy instance
     */
    private final NoteProxy noteProxy;

    /**
     * PatientProxy instance
     */
    private final PatientProxy patientProxy;

    /**
     * Constructor for AssessmentServiceImpl.
     * @param noteProxy
     * @param patientProxy
     */
    public AssessmentServiceImpl(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
    }


    /**
     * Get the age of the patient based on their date of birth.
     * @param patient
     * @return patient age
     */
    public long getPatientAge(PatientBean patient) {
        return ChronoUnit.YEARS.between(patient.getBirthday(), LocalDate.now());
    }


    /**
     * Calculates the risk level of a patient based on their age,
     * gender, and the number of trigger terms present in their notes.
     * @param patient to calculate risk
     * @param notes   of patient
     * @return status of patient
     */

    public RiskLevel calculRisks(PatientBean patient, List<NoteBean> notes) {

        // Calculate the number of trigger terms present in the patient's notes
        long nbTriggers = calculateTriggerTerms(notes);

        // Get the patient's age
        long age = getPatientAge(patient);

        // Initialize the risk level as NONE
        RiskLevel status = RiskLevel.NONE;

        // Calculate the risk level based on the number of trigger terms and the patient's age and gender
        if ((age > 30 && nbTriggers >= 8) ||
                (Gender.FEMALE.equals(patient.getGender()) && age < 30 && nbTriggers >= 7) ||
                (Gender.MALE.equals(patient.getGender()) && age < 30 && nbTriggers >= 5)
        ) {
            status = RiskLevel.EARLY_ONSET;
        } else if ((age > 30 && nbTriggers >= 6) ||
                (Gender.FEMALE.equals(patient.getGender()) && age < 30 && nbTriggers >= 4 && nbTriggers <= 6) ||
                (Gender.MALE.equals(patient.getGender()) && age < 30 && nbTriggers >= 3 && nbTriggers <= 4)
        ) {
            status = RiskLevel.IN_DANGER;
        } else if (age > 30 && nbTriggers >= 2
        ) {
            status = RiskLevel.BORDERLINE;
        }

        return status;
    }

    /**
     * Calculate the number of trigger terms in a list of notes.
     * @param notes filter notes assessment terminology
     * @return The number of trigger terms in the notes.
     */

    public long calculateTriggerTerms(List<NoteBean> notes) {

        // Combine all the notes into a single string, then trim and convert to uppercase
        String noteToStream = notes.stream()
                .map(NoteBean::getMessage)
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.joining());

        // Check how many terms in the assessment terminology are found in the notes
        long nbTriggers = Arrays.stream(AssessmentTerminology.TERMINOLOGY.toArray(new String[0]))
                .map(String::toUpperCase)
                .filter(noteToStream::contains)
                .distinct()
                .count();

        return nbTriggers;

    }

    /**
     * Get the riskLevel rapport for a patient based on their ID.
     * @param patientId to rapport risk level
     * @return rapport of risk level
     */
    @Override
    public PatientBeanDto getRapportById(Long patientId) {
        PatientBean patient = patientProxy.getPatient(patientId);
        List<NoteBean> notes = noteProxy.getNoteByPatientId(patientId);
        RiskLevel status = calculRisks(patient, notes);
        PatientBeanDto rapport = new PatientBeanDto(patient.getFirstName(), patient.getLastName(), getPatientAge(patient), patient.getGender(), status);
        return rapport;
    }

    /**
     * Get the riskLevel rapport for a patient based on their familyName
     * @param familyName
     * @return riskLevel rapport
     */
    @Override
    public PatientBeanDto getRapportByFamilyName(String familyName)  {
        List<PatientBean> patients = patientProxy.getPatientByFirstName(familyName);
        PatientBean patient = patients.get(0);
        List<NoteBean> notes = noteProxy.getNoteByPatientId(patient.getId());
        RiskLevel status = calculRisks(patient, notes);
        PatientBeanDto rapport = new PatientBeanDto(patient.getFirstName(), patient.getLastName(), getPatientAge(patient), patient.getGender(), status);
        return rapport;
    }


}

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

@Service
public class AssessmentServiceImpl implements IAssessmentService {

    private static final Logger logger = LogManager.getLogger("AssessmentImpl");

    private final NoteProxy noteProxy;

    private final PatientProxy patientProxy;

    public AssessmentServiceImpl(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
    }


    /**
     * Get the age of the patient
     *
     * @param patient
     * @return patient age
     */
    public long getPatientAge(PatientBean patient) {
        return ChronoUnit.YEARS.between(patient.getBirthday(), LocalDate.now());
    }


    /**
     * @param patient to calculate risk
     * @param notes   of patient
     * @return status of patient
     */

    public RiskLevel calculRisks(PatientBean patient, List<NoteBean> notes) {
        long nbTriggers = calculateTriggerTerms(notes);
        long age = getPatientAge(patient);
        RiskLevel status = RiskLevel.NONE;


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
     * @param notes filter notes assessment terminology
     * @return long number of terminology
     */

    public long calculateTriggerTerms(List<NoteBean> notes) {

        String noteToStream = notes.stream()
                .map(NoteBean::getMessage)
                .map(String::trim)
                .collect(Collectors.joining());

        long nbTriggers = Arrays.stream(AssessmentTerminology.TERMINOLOGY.toArray(new String[0]))
                .filter(noteToStream::contains)
                .distinct()
                .count();

        return nbTriggers;

    }

    /**
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


}

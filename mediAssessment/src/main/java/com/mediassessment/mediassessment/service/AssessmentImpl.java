package com.mediassessment.mediassessment.service;

import com.mediassessment.mediassessment.beans.NoteBean;
import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.proxies.NoteProxy;
import com.mediassessment.mediassessment.proxies.PatientProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AssessmentImpl implements IAssessment {

    private static final Logger logger = LogManager.getLogger("AssessmentImpl");

    NoteProxy noteProxy;

    PatientProxy patientProxy;


    /**
     * Get the age of the patient
     * @param patient
     * @return patient age
     */
    public long getPatientAge (PatientBean patient) {
        return ChronoUnit.YEARS.between(patient.getBirthday(), LocalDate.now());
    }


    @Override
    public String diabetesAssessment(Long id) {
        PatientBean patient = patientProxy.getPatient(id);
        List<NoteBean> patientNotes = noteProxy.getNoteByPatientId(id);


        return null;
    }


}

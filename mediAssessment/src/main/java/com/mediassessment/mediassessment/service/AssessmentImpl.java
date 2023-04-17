package com.mediassessment.mediassessment.service;

import com.mediassessment.mediassessment.beans.NoteBean;
import com.mediassessment.mediassessment.beans.PatientBean;
import com.mediassessment.mediassessment.proxies.NoteProxy;
import com.mediassessment.mediassessment.proxies.PatientProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentImpl implements IAssessment {

    NoteProxy noteProxy;

    PatientProxy patientProxy;

    @Override
    public String diabetesAssessment(Long id) {
        PatientBean patient = patientProxy.getPatient(id);
        List<NoteBean> patientNotes = noteProxy.getNoteByPatientId(id);


        return null;
    }


}

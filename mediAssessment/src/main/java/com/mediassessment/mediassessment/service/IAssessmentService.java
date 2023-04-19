package com.mediassessment.mediassessment.service;

import com.mediassessment.mediassessment.beans.dto.PatientBeanDto;

public interface IAssessmentService {


    PatientBeanDto getRapportById(Long patientId);
}

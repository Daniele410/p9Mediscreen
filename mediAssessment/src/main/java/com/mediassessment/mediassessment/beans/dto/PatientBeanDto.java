package com.mediassessment.mediassessment.beans.dto;

import com.mediassessment.mediassessment.constant.Gender;
import com.mediassessment.mediassessment.constant.RiskLevel;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientBeanDto {

    private Long id;

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Gender gender;

    RiskLevel riskLevel;

    public PatientBeanDto() {
    }

    public PatientBeanDto(Long id, String firstName, String lastName, LocalDate birthday, Gender gender, RiskLevel riskLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.riskLevel = riskLevel;
    }

    public PatientBeanDto(String lastName, String firstName, Gender gender, long patientAge, RiskLevel riskLevel) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }
}

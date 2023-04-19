package com.clientui.mediclientui.beans.dto;


import com.clientui.mediclientui.constant.Gender;
import com.clientui.mediclientui.constant.RiskLevel;

public class PatientBeanDto {


    private String firstName;

    private String lastName;

    private long age;
    private Gender gender;

    RiskLevel riskLevel;

    public PatientBeanDto() {
    }

    public PatientBeanDto(String firstName, String lastName, long age, Gender gender, RiskLevel riskLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.riskLevel = riskLevel;
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

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
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

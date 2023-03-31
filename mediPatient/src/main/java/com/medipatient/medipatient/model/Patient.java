package com.medipatient.medipatient.model;

import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Patient {

    @ApiParam(
            value = "id of patient",
            example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ApiParam(
            value = "firstName of patient",
            example = "Mario")
    @Column(nullable = false)
    @NotBlank(message = "required")
    private String firstName;

    @ApiParam(
            value = "lastName of patient",
            example = "Bros")
    @Column(nullable = false)
    @NotBlank(message = "required")
    private String lastName;

    @ApiParam(
            value = "birthday of patient",
            example = "10-11-1985")
    @Column(nullable = false)
    private LocalDate birthday;

    //Gender
    @ApiParam(
            value = "birthday of patient",
            example = "F, M")
    @Column(nullable = false)
    private String sex;

    private String address;

    private String phone;

    public Patient() {
    }

    public Patient(Long id, String firstName, String lastName, LocalDate birthday, String sex, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package com.clientui.mediclientui.beans;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class NoteBean {


    private String id;

    private Long patientId;
    @Min(3)
    @NotBlank(message = "Insert Message")
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();


    public NoteBean() {
    }

    public NoteBean(String id, Long patientId, String message, LocalDate date) {
        this.id = id;
        this.patientId = patientId;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

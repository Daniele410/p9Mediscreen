package com.medinote.medinote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "note")
public class Note {

    @Id
    private String id;
    @NotNull
    private Long patientId;
    @NotBlank
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    public Note() {
    }

    public Note(String id, long patientId, String message, LocalDate date) {
        this.id = id;
        this.patientId = patientId;
        this.message = message;
        this.date = date;
    }

    public Note(Long patientId, String message) {
        this.patientId = patientId;
        this.message = message;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

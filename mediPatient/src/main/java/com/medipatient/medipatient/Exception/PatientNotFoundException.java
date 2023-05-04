package com.medipatient.medipatient.Exception;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String message){
        super(message);
    }
}

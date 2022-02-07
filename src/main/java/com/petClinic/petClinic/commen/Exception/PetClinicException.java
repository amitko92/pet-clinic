package com.petClinic.petClinic.commen.Exception;

public class PetClinicException extends Exception{

    private String message;

    public PetClinicException(String message){

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PetClinicException{" +
                "message='" + message + '\'' +
                '}';
    }
}

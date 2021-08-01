package com.stackroute.wellnessservice.exception;

public class HelplineNotFoundException extends Exception{
    private String message;

    public HelplineNotFoundException(String message) {
        super();
        this.message = message;
    }
}

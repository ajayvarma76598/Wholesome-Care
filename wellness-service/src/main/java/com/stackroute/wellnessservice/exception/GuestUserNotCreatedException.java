package com.stackroute.wellnessservice.exception;

public class GuestUserNotCreatedException extends Exception{
    private String message;

    public GuestUserNotCreatedException(String message) {
        super();
        this.message = message;
    }
}

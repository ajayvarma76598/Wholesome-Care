package com.stackroute.graphcommandservice.Exception;

public class NodeAlreadyExistsException extends Exception{

    private String message;

    public NodeAlreadyExistsException(String message) {
        this.message = message;
    }

    public NodeAlreadyExistsException() {
    }
}

package com.stackroute.graphcommandservice.Exception;

public class NodeNotFoundException extends Exception{
    private String message;

    public NodeNotFoundException(String message) {
        this.message = message;
    }

    public NodeNotFoundException() {
    }
}

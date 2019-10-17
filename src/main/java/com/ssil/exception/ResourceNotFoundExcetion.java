package com.ssil.exception;

public class ResourceNotFoundExcetion extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResourceNotFoundExcetion(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ResourceNotFoundExcetion() {
        super();
    }
}

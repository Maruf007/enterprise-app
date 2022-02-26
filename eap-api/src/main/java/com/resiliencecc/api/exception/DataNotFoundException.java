package com.resiliencecc.api.exception;

public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = -9071908695250414603L;
    private static final String MESSAGE = "Data not found into system.";

    public DataNotFoundException() {
        super(MESSAGE);
    }

    public DataNotFoundException(final String message) {
        super(message);
    }

}

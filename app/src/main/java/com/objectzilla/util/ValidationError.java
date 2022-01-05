package com.objectzilla.util;

public class ValidationError extends Exception {
    public ValidationError(String errorMessage) {
        super(errorMessage);
    }
}

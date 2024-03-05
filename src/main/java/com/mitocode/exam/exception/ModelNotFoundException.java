package com.mitocode.exam.exception;

public class ModelNotFoundException extends  RuntimeException {

    public ModelNotFoundException(String message) {
        super(message);
    }
}

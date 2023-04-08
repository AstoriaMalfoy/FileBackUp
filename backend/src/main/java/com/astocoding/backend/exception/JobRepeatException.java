package com.astocoding.backend.exception;

public class JobRepeatException extends RuntimeException{

    public JobRepeatException(String message) {
        super(message);
    }
}

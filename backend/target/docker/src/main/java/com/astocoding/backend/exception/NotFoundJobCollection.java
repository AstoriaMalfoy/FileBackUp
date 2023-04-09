package com.astocoding.backend.exception;

public class NotFoundJobCollection extends RuntimeException{
    public NotFoundJobCollection(String message) {
        super(message);
    }
}

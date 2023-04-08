package com.astocoding.backend.exception;

import lombok.Data;

@Data
public class FileSourceAccessException extends RuntimeException{
    public FileSourceAccessException(String message) {
        super(message);
    }
}

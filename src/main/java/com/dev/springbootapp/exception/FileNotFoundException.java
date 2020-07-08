package com.dev.springbootapp.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}

package com.udacity.jwdnd.course1.cloudstorage.exception;

public class IdGenerationException extends Exception {
    public IdGenerationException(String msg, Throwable t) {
        super(msg, t);
    }
    public IdGenerationException(String msg) {
        super(msg);
    }
}

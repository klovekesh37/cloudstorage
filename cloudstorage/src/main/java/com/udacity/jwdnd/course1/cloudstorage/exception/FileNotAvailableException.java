package com.udacity.jwdnd.course1.cloudstorage.exception;

public class FileNotAvailableException extends Exception {
    public FileNotAvailableException(String msg, Throwable t) {
        super(msg, t);
    }
    public FileNotAvailableException(String msg) {
        super(msg);
    }
}
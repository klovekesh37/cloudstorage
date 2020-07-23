package com.udacity.jwdnd.course1.cloudstorage.exception;

public class FileNameAlreadyExistsException extends Exception {
    public FileNameAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }
    public FileNameAlreadyExistsException(String msg) {
        super(msg);
    }
}

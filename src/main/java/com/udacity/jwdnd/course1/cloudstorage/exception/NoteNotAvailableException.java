package com.udacity.jwdnd.course1.cloudstorage.exception;

public class NoteNotAvailableException extends Exception {
    public NoteNotAvailableException(String msg, Throwable t) {
        super(msg, t);
    }
    public NoteNotAvailableException(String msg) {
        super(msg);
    }
}
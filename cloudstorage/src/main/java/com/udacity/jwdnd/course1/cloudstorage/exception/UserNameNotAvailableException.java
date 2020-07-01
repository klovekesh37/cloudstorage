package com.udacity.jwdnd.course1.cloudstorage.exception;

public class UserNameNotAvailableException extends Exception {
    public UserNameNotAvailableException(String msg, Throwable t) {
        super(msg, t);
    }
    public UserNameNotAvailableException(String msg) {
        super(msg);
    }
}

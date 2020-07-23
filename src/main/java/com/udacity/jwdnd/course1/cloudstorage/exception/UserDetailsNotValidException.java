package com.udacity.jwdnd.course1.cloudstorage.exception;

public class UserDetailsNotValidException extends Exception {
    public UserDetailsNotValidException(String msg, Throwable t) {
        super(msg, t);
    }
    public UserDetailsNotValidException(String msg) {
        super(msg);
    }
}


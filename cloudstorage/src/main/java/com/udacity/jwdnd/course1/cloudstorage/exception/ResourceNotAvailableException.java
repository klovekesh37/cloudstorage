package com.udacity.jwdnd.course1.cloudstorage.exception;

import org.springframework.security.core.AuthenticationException;

public class ResourceNotAvailableException extends Exception {
    public ResourceNotAvailableException(String msg, Throwable t) {
        super(msg, t);
    }
    public ResourceNotAvailableException(String msg) {
        super(msg);
    }
}

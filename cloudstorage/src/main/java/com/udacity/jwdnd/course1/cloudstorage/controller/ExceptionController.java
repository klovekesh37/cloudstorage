package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@PropertySource(value = "classpath:message.properties")
public class ExceptionController {

    @Value("${user.id.error}")
    String userIdError;

    @Value("${file.error.size}")
    String fileSizeError;

    @ExceptionHandler(UserNameNotAvailableException.class)
    public String handleUserNameExistsException(UserNameNotAvailableException ex,Model model) {
        model = getModel(ex,model);
        return "signup";
    }

    @ExceptionHandler(CredentialNotAvailableException.class)
    public String handleCredentialNotAvailableException(CredentialNotAvailableException ex, Model model) {
        model = getModel(ex,model);
        return "result";
    }

    @ExceptionHandler(FileNameAlreadyExistsException.class)
    public String handleFileNameAlreadyExistsException(FileNameAlreadyExistsException ex,Model model) {
        model = getModel(ex,model);
        return "result";
    }

    @ExceptionHandler(FileNotAvailableException.class)
    public String handleFileNotAvailableException(FileNotAvailableException ex, Model model) {
        model = getModel(ex, model);
        return "result";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex,Model model)
    {
        model.addAttribute("error", true);
        model.addAttribute("message",fileSizeError);
        return "result";
    }

    @ExceptionHandler(IdGenerationException.class)
    public String handleIdGenerationException(IdGenerationException ex, Model model) {
        model = getModel(ex, model);
        if(ex.getMessage().equalsIgnoreCase(userIdError))
            return "signup";
        return "result";
    }

    @ExceptionHandler(NoteNotAvailableException.class)
    public String handleNoteNotAvailableException(NoteNotAvailableException ex, Model model) {
        model = getModel(ex, model);
        return "result";
    }

    @ExceptionHandler(ResourceNotAvailableException.class)
    public String handleResourceNotAvailableException(ResourceNotAvailableException ex, Model model) {
        model = getModel(ex, model);
        return "result";
    }

    @ExceptionHandler(UserDetailsNotValidException.class)
    public String handleUserDetailsNotValidExceptions(UserDetailsNotValidException ex, Model model) {
        model = getModel(ex, model);
        return "signup";
    }

    public Model getModel(Exception ex, Model model) {
        model.addAttribute("error", true);
        model.addAttribute("message",ex.getMessage());
        return model;
    }
}

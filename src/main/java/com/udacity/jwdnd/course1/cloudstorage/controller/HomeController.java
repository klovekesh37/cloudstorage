package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/", "/home"})
public class HomeController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private FileService fileService;

    @GetMapping
    public String getHomePage(Model model) throws ResourceNotAvailableException {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("credentials", credentialService.getAllCredentials());
        model.addAttribute("files", fileService.getAllFiles());
        return "home";
    }
}

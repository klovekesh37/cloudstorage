package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("id") Long credentialId, Model model) throws CredentialNotAvailableException, ResourceNotAvailableException {
        credentialService.deleteCredential(credentialId);
            model.addAttribute("success", true);
            return "result";
    }

    @PostMapping
    public String updateOrCreateCredential(Credential credential, Model model) throws IdGenerationException, ResourceNotAvailableException {
        credentialService.updateOrCreateCredential(credential);
        model.addAttribute("success",true);
        return "result";
    }
}

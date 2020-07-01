package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.UserDetailsNotValidException;
import com.udacity.jwdnd.course1.cloudstorage.exception.UserNameNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute User user, Model model) throws IdGenerationException, ResourceNotAvailableException, UserNameNotAvailableException, UserDetailsNotValidException {
        userService.createUser(user);
        model.addAttribute("signup",true);
        return "login";
    }
}

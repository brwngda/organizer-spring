package com.gbarwinski.organizerspring.authentication.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gbarwinski.organizerspring.utility.Attributes.LOGIN_ERROR;


@RestController
public class Login {

    @GetMapping({"/login"})
    public String projects(Model model) {
        return "login/loginPage";
    }

    @GetMapping("/login-error")
    public String login_error(Model model) {
        model.addAttribute(LOGIN_ERROR, true);
        return "login/loginPage";
    }
}
package com.gbarwinski.organizerspring.authentication.controller;


import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.gbarwinski.organizerspring.utility.Attributes.APP_USER;
import static com.gbarwinski.organizerspring.utility.Attributes.LOGIN_ERROR;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login/loginPage";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute(LOGIN_ERROR, true);
        return "login/loginPage";
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(APP_USER);
        return "login/loginPage";
    }
}
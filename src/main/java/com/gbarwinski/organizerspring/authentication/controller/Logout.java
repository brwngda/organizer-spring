package com.gbarwinski.organizerspring.authentication.controller;

import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Logout {

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("appUser");
        return "login/loginPage";
    }
}
package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomePageController {

    @GetMapping({"/home", "/"})
    public String showHomePage(Model model) {
        model.addAttribute("presentYear", LocalDateTime.now().getYear());
        return "homePage/index";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null)
            appUser = (User) session.getAttribute("appUser");
        if (appUser != null) {
            model.addAttribute("ActualUser", appUser);
        } else {
            model.addAttribute("ActualUser", new User());
        }
    }
}
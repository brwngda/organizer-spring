package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;

import static com.gbarwinski.organizerspring.utility.Attributes.*;

@Controller
public class HomePageController {

    @GetMapping({"/home", "/"})
    public String showHomePage(Model model) {
        model.addAttribute(PRESENT_YEAR, LocalDateTime.now().getYear());
        return "homePage/index";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null)
            appUser = (User) session.getAttribute(APP_USER);
        if (appUser != null) {
            model.addAttribute(ACTUAL_USER, appUser);
        } else {
            model.addAttribute(ACTUAL_USER, new User());
        }
    }
}
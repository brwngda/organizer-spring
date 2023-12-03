package com.gbarwinski.organizerspring.authentication.controller;

import com.gbarwinski.organizerspring.DTO.UserDTO;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.service.LoggingUserService;
import com.gbarwinski.organizerspring.service.RabbitService;
import com.gbarwinski.organizerspring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

import static com.gbarwinski.organizerspring.utility.Attributes.NEW_USER;
import static com.gbarwinski.organizerspring.utility.Attributes.REGISTRATION_SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Controller
public class Registration {
    private final UserService userService;
    private final LoggingUserService loggingUserService;
    private final RabbitService rabbitService;

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute(NEW_USER, userDTO);
        return "login/registrationPage";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("newUser") @Valid UserDTO userDTO, BindingResult result, Errors errors, Model model) {
        User registered = new User();
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            result.rejectValue("matchingPassword", "error.newUser", "Passwords don't match");
            return "login/registrationPage";
        }
        if (!userDTO.getEmail().isEmpty() && userService.exists(userDTO.getEmail())) {
            result.rejectValue("email", "error.newUser", "Username exists!");
            return "login/registrationPage";
        }

        if (!result.hasErrors()) {
            registered = createUserAccount(userDTO);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        model.addAttribute(REGISTRATION_SUCCESS, true);
        assert registered != null;
        String userId = registered.getIdUser().toString();
        rabbitService.createQueue(userId);

        return "login/loginPage";
    }

    private User createUserAccount(UserDTO userDTO) {
        User registered = null;
        try {
            registered = loggingUserService.registerNewUserAccount(userDTO);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return registered;
    }
}
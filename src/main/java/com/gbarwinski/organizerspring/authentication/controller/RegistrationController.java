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
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;

import static com.gbarwinski.organizerspring.utility.Attributes.NEW_USER;
import static com.gbarwinski.organizerspring.utility.Attributes.REGISTRATION_SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final LoggingUserService loggingUserService;
    private final RabbitService rabbitService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute(NEW_USER, new UserDTO());
        return "login/registrationPage";
    }

    @PostMapping
    public String addUser(
            @ModelAttribute("newUser") @Valid UserDTO userDTO,
            BindingResult result,
            Errors errors,
            Model model
    ) {
        if (result.hasErrors()) {
            log.error("Validation errors: {}", result.getAllErrors());
            result.rejectValue("matchingPassword", "error.newUser", "Passwords don't match");
            return "login/registrationPage";
        }
        if (!userDTO.getEmail().isEmpty() && userService.exists(userDTO.getEmail())) {
            result.rejectValue("email", "error.newUser", "Username exists!");
            return "login/registrationPage";
        }

        Optional<User> registeredUser = createUserAccount(userDTO);
        if (registeredUser.isEmpty()) {
            result.rejectValue("email", "message.regError");
            return "login/registrationPage";
        }

        model.addAttribute(REGISTRATION_SUCCESS, true);
        registeredUser.ifPresent(user -> {
            try {
                rabbitService.createQueue(user.getUserId().toString());
            } catch (Exception e) {
                log.error("Cannot create rabbitmq queue.", e);
            }
        });
        return "login/loginPage";
    }

    private Optional<User> createUserAccount(UserDTO userDTO) {
        try {
            return Optional.of(loggingUserService.registerNewUserAccount(userDTO));
        } catch (IOException e) {
            log.error("Error while registering user account: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
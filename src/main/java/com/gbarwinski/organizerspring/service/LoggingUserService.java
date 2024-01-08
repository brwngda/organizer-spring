package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.UserDTO;
import com.gbarwinski.organizerspring.model.*;
import com.gbarwinski.organizerspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoggingUserService implements IUserService {

    private final UserRepository userRepository;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO accountDto) throws IOException {
        if (emailExist(accountDto.getEmail())) {
            throw new IOException("There is an account with that email address: " + accountDto.getEmail());
        }
        return saveUser(accountDto);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private User saveUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setName(userDTO.getFirstName());
        newUser.setSurname(userDTO.getSecondName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(userDTO.getRoles());
        newUser.setNick(userDTO.getNick());
        userRepository.save(newUser);
        log.info("User '{}' added", newUser.getEmail());
        addInitialProjectAndTask(newUser);
        return newUser;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void addInitialProjectAndTask(User newUser) {
        Project initialProject = projectService.addInitialProject(newUser);
        Task task = Task.builder()
                .name("New request")
                .description("Description")
                .project(initialProject)
                .priority(Priority.LOW)
                .typeOfStory(TypeOfStory.STORY)
                .progress("Backlog")
                .build();
        taskService.saveTask(task);
    }
}
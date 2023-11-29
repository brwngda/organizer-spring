package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.exception.NoUserFoundException;
import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.repository.ProjectRepository;
import com.gbarwinski.organizerspring.repository.UserRepository;
import com.gbarwinski.organizerspring.utility.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoUserFoundException(id));
    }

    public boolean exists(String email) {
        User userByEmail = userRepository.findByEmail(email);
        return userByEmail != null;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersApartActiveUser() {
        List<User> all = getAllUsers();
        Long activeUserId = getActiveUserId();
        return all.stream()
                .filter(user -> !user.getIdUser().equals(activeUserId))
                .collect(Collectors.toList());
    }


    public List<User> getAllUsersAssignedToProject(Long projectId) {
        List<User> all = getAllUsers();
        Optional<Project> ProjectById = projectRepository.findById(projectId);

        List<User> collect = new ArrayList<>();
        if (ProjectById.isPresent()) {
            collect = all.stream()
                    .filter(user -> user.getProjects().stream().
                            anyMatch(project -> project.getId().equals(projectId)))
                    .distinct()
                    .collect(Collectors.toList());
        }
        return collect;
    }

    public List<User> getAllUsersAssignedToProjectApartActiveUser(Long projectId) {
        List<User> allUsersAssignedToProject = getAllUsersAssignedToProject(projectId);
        allUsersAssignedToProject.remove(UtilityClass.getLoggedInUser());
        return allUsersAssignedToProject;
    }


    private Long getActiveUserId() {

        return UtilityClass.getLoggedInUser().getIdUser();
    }

    public String getInitialLetters(User user) {
        return user.getName().toUpperCase().substring(0, 1) + user.getSurname().toUpperCase().substring(0, 1);
    }

}
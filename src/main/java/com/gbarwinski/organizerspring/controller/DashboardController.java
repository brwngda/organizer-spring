package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.model.Sprint;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gbarwinski.organizerspring.utility.Attributes.*;

@ControllerAdvice
@Data
@RequiredArgsConstructor
@RestController
public class DashboardController {

    private final ProjectService projectService;
    private final ProgressService progressService;
    private final TaskService taskService;
    private final UserService userService;
    private final SprintService sprintService;
    private final MessageService messageService;

    @GetMapping("/dashboard")
    public String showDashBoard(@RequestParam("id") Long projectId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(APP_USER) == null) {
            return "redirect:/logout";
        }
        Project actualProject = projectService.findProjectById(projectId);
        model.addAttribute(ACTUAL_DASHBOARD, actualProject);
        model.addAttribute(TASK_LIST, taskService.getTasksByProject(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT, userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT_APART_ACTIVE_USER, userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        session.setAttribute(ACTUAL_DASHBOARD, actualProject);
        return "fragments_dashboard/dashBoard";
    }

    @GetMapping("/dashboard/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsersApartActiveUser();
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User appUser = null;
        Project actualProject = null;
        if (session != null) {
            appUser = (User) session.getAttribute(APP_USER);
            actualProject = (Project) session.getAttribute(ACTUAL_DASHBOARD);
        }
        if (appUser != null) {
            model.addAttribute(ACTUAL_USER, appUser);
            model.addAttribute(ACTUAL_USER_INITIAL_LETTERS, userService.getInitialLetters(appUser));
            model.addAttribute(ACTUAL_DASHBOARD, actualProject);
            model.addAttribute(PROGRESS_STEPS, progressService.findAllProgress());
            model.addAttribute(PROJECT_LIST, projectService.getAllProjectsForUser());
            model.addAttribute(SPRINT_LIST, sprintService.findAll());
            model.addAttribute(SPRINT, new Sprint());
            model.addAttribute(TASK_LIST, taskService.getTasksByProject(actualProject));
            model.addAttribute(USER_LIST, userService.getAllUsersApartActiveUser());
            model.addAttribute(LOGS_ABOUT_PROJECTS, messageService.getLastFiveMessagesForActiveUser(appUser.getIdUser()));
        }
    }
}
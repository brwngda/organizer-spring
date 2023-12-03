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
        if (session.getAttribute("appUser") == null) return "redirect:/logout";
        Project actualProject = projectService.findProjectById(projectId);
        model.addAttribute("actualDashBoard", actualProject);
        model.addAttribute("taskList", taskService.getTasksByProject(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute("usersAssignedToProjectApartActiveUser", userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        session.setAttribute("actualDashBoard", actualProject);
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
            appUser = (User) session.getAttribute("appUser");
            actualProject = (Project) session.getAttribute("actualDashBoard");
        }
        if (appUser != null) {
            model.addAttribute("ActualUser", appUser);
            model.addAttribute("ActualUserInitialLetters", userService.getInitialLetters(appUser));
            model.addAttribute("actualDashBoard", actualProject);
            model.addAttribute("progress_steps", progressService.findAllProgress());
            model.addAttribute("projectList", projectService.getAllProjectsForUser());
            model.addAttribute("sprintList", sprintService.findAll());
            model.addAttribute("sprint", new Sprint());
            model.addAttribute("taskList", taskService.getTasksByProject(actualProject));
            model.addAttribute("userList", userService.getAllUsersApartActiveUser());
            model.addAttribute("logsAboutProjects", messageService.getLastFiveMessagesForActiveUser(appUser.getIdUser()));
        }
    }
}
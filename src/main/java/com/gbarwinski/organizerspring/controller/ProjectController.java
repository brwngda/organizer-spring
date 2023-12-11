package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.DTO.ProjectDTO;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.service.MessageService;
import com.gbarwinski.organizerspring.service.ProjectService;
import com.gbarwinski.organizerspring.service.UserService;
import com.gbarwinski.organizerspring.utility.UtilityClass;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.gbarwinski.organizerspring.utility.Attributes.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/projects")
    public String showProjects(Model model, HttpServletRequest request) {
        return "templates/fragments_projects/browserProject.html";
    }

    @GetMapping("/createProject")
    public String createProject(Model model) {
        model.addAttribute("newProject", new ProjectDTO());
        return "templates/fragments_projects/addProject";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute("newProject") @Valid ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "templates/fragments_projects/addProject";
        }
        projectService.createProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/editproject")
    public String editProject(@RequestParam("id") Long projectId, Model model) {
        model.addAttribute(OLD_PROJECT, projectService.findProjectAndTransferToDTO(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT, userService.getAllUsersAssignedToProject(projectId));
        return "fragments_projects/editProject";
    }

    @PostMapping("/editproject")
    public String editProject(Model model, @ModelAttribute("oldProject") ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "fragments_projects/addProject";
        }
        projectService.updateProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }

    @GetMapping("/project/{projectId}/{userId}")
    public String addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.addUserToProject(projectId, userId);
        return "Result: positive";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null) appUser = (User) session.getAttribute("appUser");
        if (appUser != null) {
            model.addAttribute(AVATAR_LIST, UtilityClass.getListOfIconTitles());
            model.addAttribute(ALL_ADMINS_INITIALS_LIST, projectService.getProjectAdminNameAndSurname());
            model.addAttribute(LOGS_ABOUT_PROJECTS, messageService.getLastFiveMessagesForActiveUser(appUser.getIdUser()));
        }
    }
}
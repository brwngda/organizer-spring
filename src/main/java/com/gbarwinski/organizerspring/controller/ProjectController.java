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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/projects")
    public String showProjects(Model model, HttpServletRequest request) {
        return "fragments_projects/browserProject";
    }

    @GetMapping("/project/{projectId}/{userId}")
    public String addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.addUserToProject(projectId, userId);
        return "Result: positive";
    }
    @GetMapping("/createProject")
    public String createProject(Model model) {
        model.addAttribute("newProject", new ProjectDTO());
        return "fragments_projects/addProject";
    }

    @PostMapping("/createProject")
    public String createProject(@ModelAttribute("newProject") @Valid ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "fragments_projects/addProject";
        }
        projectService.createProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/editproject")
    public String editProject(@RequestParam("id") Long projectId, Model model) {
        model.addAttribute("oldProject", projectService.findProjectAndTransferToDTO(projectId));
        model.addAttribute("usersAssignedToProject", userService.getAllUsersAssignedToProject(projectId));
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

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        User appUser = null;
        if (session != null) appUser = (User) session.getAttribute("appUser");
        if (appUser != null) {
            model.addAttribute("avatarList", UtilityClass.getListOfIconTitles());
            model.addAttribute("allAdminsInitialsList", projectService.getProjectAdminNameAndSurname());
            model.addAttribute("logsAboutProjects", messageService.getLastFiveMessagesForActiveUser(appUser.getIdUser()));
        }
    }
}
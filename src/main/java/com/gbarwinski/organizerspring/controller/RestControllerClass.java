package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.DTO.ProjectDTO;
import com.gbarwinski.organizerspring.model.Task;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.service.ProjectService;
import com.gbarwinski.organizerspring.service.TaskService;
import com.gbarwinski.organizerspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor

public class RestControllerClass {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/project/{projectId}/{userId}")
    public String addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.addUserToProject(projectId, userId);
        return "Result: positive";
    }

    @GetMapping("/dashboard/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsersApartActiveUser();
    }

    @GetMapping("/task/{taskId}/{progress}")
    public void updateCardProgress(@PathVariable("taskId") Long taskId, @PathVariable("progress") String progress) {
        Task task = taskService.findTask(taskId);
        task.setProgress(progress);
        taskService.saveTask(task);
    }

    @PostMapping("/editProject")
    public String editProject(Model model, @ModelAttribute("oldProject") ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "fragments_projects/addProject";
        }
        projectService.updateProject(projectDTO);
        return "redirect:/projects";
    }
}

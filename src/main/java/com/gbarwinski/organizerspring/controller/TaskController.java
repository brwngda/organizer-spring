package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.DTO.TaskDTO;
import com.gbarwinski.organizerspring.model.Task;
import com.gbarwinski.organizerspring.service.TaskService;
import com.gbarwinski.organizerspring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.gbarwinski.organizerspring.utility.Attributes.*;

@ControllerAdvice
@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    @GetMapping("/createTask")
    public String addCard(@RequestParam("id") Long projectId, Model model) {
        model.addAttribute(NEW_TASK, taskService.newTaskDtoWithAssignedProjectId(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT, userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT_APART_ACTIVE_USER, userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        return "fragments_dashboard/createTask";
    }

    @PostMapping("/createTask")
    public String postCard(@ModelAttribute("newTask") @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        Long projectId = taskService.getProjectIdFromTaskDTO(taskDTO);
        if (bindingResult.hasErrors()) {
            return "fragments_dashboard/createTask";
        }
        taskService.createTaskUsingTaskDTO(taskDTO);
        return "redirect:/dashboard?id=" + projectId;
    }

    @PostMapping("/addcard")
    public String postCard(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/dashboard";
    }

    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id, Model model) {
        TaskDTO taskDTO = taskService.getTaskDtoFromTask(id);
        model.addAttribute("task", taskDTO);
        Long projectId = taskService.getProjectIdFromTaskDTO(taskDTO);
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT, userService.getAllUsersAssignedToProject(projectId));
        model.addAttribute(USERS_ASSIGNED_TO_PROJECT_APART_ACTIVE_USER, userService.getAllUsersAssignedToProjectApartActiveUser(projectId));
        return "fragments_dashboard/editTask";
    }

    @PostMapping("/edittask")
    public String editCard(@ModelAttribute("task") TaskDTO taskDTO, @RequestParam("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "fragments_dashboard/editTask";
        }
        taskService.updateTaskUsingDTO(taskDTO, id);
        return "redirect:/dashboard?id=" + taskDTO.getProject().getId();
    }

    @GetMapping("/deletetask")
    public String deleteCard(@RequestParam("id") Long id) {
        Long projectId = taskService.getProjectIdFromTask(id);
        taskService.deleteTask(id);
        return "redirect:/dashboard?id=" + projectId;
    }

    @GetMapping("/task/{taskId}/{progress}")
    public void updateCardProgress(@PathVariable("taskId") Long taskId, @PathVariable("progress") String progress) {
        Task task = taskService.findTask(taskId);
        task.setProgress(progress);
        taskService.saveTask(task);
    }


}

package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.DTO.TaskDTO;
import com.gbarwinski.organizerspring.model.Task;
import com.gbarwinski.organizerspring.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/addcard")
    public String postCard(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/dashboard";
    }

    @GetMapping("/edittask")
    public String editCard(@RequestParam("id") Long id, Model model) {
        TaskDTO taskDTO = taskService.getTaskDtoFromTask(id);
        model.addAttribute("task", taskDTO);
        return "fragments/edit_card";
    }

    @PostMapping("/edittask")
    public String editCard(Model model, @ModelAttribute("task") TaskDTO taskDTO, @RequestParam("id") Long id) {
        taskService.updateTaskUsingDTO(taskDTO, id);
        return "redirect:/dashboard";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("newTask", new Task());
    }
}
package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.service.ProgressService;
import com.gbarwinski.organizerspring.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@Data
@RequiredArgsConstructor
@RestController
public class DashboardController {

    private final ProjectService projectService;
    private final ProgressService progressService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        return "main/dashboard";
    }

    @ModelAttribute
    public void AddAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("projectList", projectService.getAllProjects());
        model.addAttribute("progress_steps", progressService.findAllProgress());
    }
}
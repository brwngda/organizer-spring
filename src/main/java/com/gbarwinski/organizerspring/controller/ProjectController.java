package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public String showProjectBar(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "fragments/project_menu";
    }
}

package com.gbarwinski.organizerspring.controller;

import com.gbarwinski.organizerspring.DTO.ProjectDTO;
import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/project/{projectId}/{userId}")
    public String addUserToProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.addUserToProject(projectId, userId);
        return "Result: positive";
    }

    @GetMapping("/star/{projectId}")
    public ProjectDTO updateProject(@PathVariable("projectId") Long projectId) {
        Project project = projectService.findProjectById(projectId);
        boolean updatedStarred = !project.isStarred();
        project.setStarred(updatedStarred);
        projectService.save(project);
        return projectService.findProjectAndTransferToDTO(projectId);
    }
}

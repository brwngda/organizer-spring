package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.ProjectDTO;
import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project findProjectById(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = new Project();
        if (optionalProject.isPresent()) {
            project = optionalProject.get();
        }
        return project;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectDTO findProjectAndTransferToDTO(Long id) {
        Project project = findProjectById(id);
        return transformProjectToProjectDTO(project);
    }

    private ProjectDTO transformProjectToProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setStarred(project.isStarred());
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setAdmin(project.getAdmin());
        projectDTO.setAvatar(project.getAvatar());
        return projectDTO;
    }

    private Project transformProjectDtoToProject(Project project, ProjectDTO projectDTO) {
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setAdmin(projectDTO.getAdmin());
        project.setAvatar(projectDTO.getAvatar());
        project.setStarred(project.isStarred());
        return project;
    }

    public void deleteProject(Long id) {
        Project project = findProjectById(id);
        projectRepository.delete(project);
    }
}
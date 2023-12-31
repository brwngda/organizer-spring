package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.ProjectDTO;
import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.repository.ProjectRepository;
import com.gbarwinski.organizerspring.utility.UtilityClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public Project findProjectById(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        Project project = new Project();
        if (optionalProject.isPresent()) {
            project = optionalProject.get();
        }
        return project;
    }

    public List<Project> findAllProjectForUser(Long userId) {
        User activeUser = userService.findUserById(userId);
        return filterProjectsForUser(activeUser);
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

    public Project addInitialProject(User user) {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Project initialProject = Project.builder()
                .name("Name of your project")
                .description("Description of your project")
                .avatar("icons/015.png")
                .users(userList)
                .admin(user.getEmail())
                .isStarred(false)
                .build();
        projectRepository.save(initialProject);
        return initialProject;
    }

    public List<Project> getAllProjectsForUser() {
        User activeUser = UtilityClass.getLoggedInUser();
        return filterProjectsForUser(activeUser);
    }

    public List<Project> filterProjectsForUser(User activeUser) {
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream()
                .filter(project -> project.getUsers().stream().
                        anyMatch(user -> user.getUserId().equals(activeUser.getUserId())))
                .collect(Collectors.toList());
    }

    public void addUserToProject(Long idProject, Long userId) {
        Optional<Project> ProjectById = projectRepository.findById(idProject);
        User userByEmail = userService.findUserById(userId);
        if (ProjectById.isPresent()) {
            Project project = ProjectById.get();
            List<User> users = project.getUsers();
            if (!users.contains(userByEmail)) {
                users.add(userByEmail);
                project.setUsers(users);
                projectRepository.save(project);
            }
        }
    }

    public void createProject(ProjectDTO projectDTO) {
        ArrayList<User> users = new ArrayList<>();
        users.add(UtilityClass.getLoggedInUser());
        Project project = new Project(projectDTO.getName(), projectDTO.getDescription(), projectDTO.getAvatar(), users, UtilityClass.getLoggedInUser().getEmail());
        save(project);
    }

    public void updateProject(ProjectDTO projectDTO) {
        Long projectId = projectDTO.getId();
        Project project = findProjectById(projectId);
        Project updatedProject = transformProjectDtoToProject(project, projectDTO);
        save(updatedProject);
    }

    public List<User> getProjectAdminNameAndSurname() {
        return getAllProjectsForUser().stream()
                .map(admin -> (userService.findUserByEmail(admin.getAdmin())))
                .distinct()
                .collect(Collectors.toList());
    }

    public void save(Project project) {
        projectRepository.save(project);
    }
}
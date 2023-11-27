package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.TaskDTO;
import com.gbarwinski.organizerspring.exception.NoTaskFoundException;
import com.gbarwinski.organizerspring.model.Project;
import com.gbarwinski.organizerspring.model.Task;
import com.gbarwinski.organizerspring.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task findTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoTaskFoundException(id));
    }

    private Task transformTaskDtoToTask(TaskDTO taskDTO, Task task) {
        task.setProject(projectService.findProjectById(taskDTO.getProject().getId()));
        task.setDescription(taskDTO.getDescription());
        task.setName(taskDTO.getName());
        task.setPriority(taskDTO.getPriority());
        task.setTypeOfStory(taskDTO.getTypeOfStory());
        task.setSprint(taskDTO.getSprint());
        task.setStoryPoints(taskDTO.getStoryPoints());
        task.setUser(taskDTO.getUser());
        return task;
    }

    public void updateTaskUsingDTO(TaskDTO taskDto, Long id) {
        Task task = findTask(id);
        transformTaskDtoToTask(taskDto, task);
        if (taskDto.getProgress() != null) task.setProgress(taskDto.getProgress());
        saveTask(task);
    }

    public TaskDTO getTaskDtoFromTask(Long id) {
        Task task = findTask(id);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setIdTask(task.getIdTask());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setName(task.getName());
        if (task.getProgress() != null) taskDTO.setProgress(task.getProgress());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setTypeOfStory(task.getTypeOfStory());
        taskDTO.setProject(projectService.findProjectById(task.getProject().getId()));
        taskDTO.setSprint(task.getSprint());
        taskDTO.setStoryPoints(task.getStoryPoints());
        taskDTO.setUser(task.getUser());
        return taskDTO;
    }

    public List<Task> getTasksByProjectId(Project project) {
        if (project != null) {
            Optional<List<Task>> AllTasksByProjectName = taskRepository.findAllByProjectId(project.getId());
            return AllTasksByProjectName.orElse(new ArrayList<Task>());
        } else {
            return new ArrayList<Task>();
        }
    }
}
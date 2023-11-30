package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.MessageDTO;
import com.gbarwinski.organizerspring.model.*;
import com.gbarwinski.organizerspring.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;

    public List<Message> getLastFiveMessagesForActiveUser(Long userId) {
        List<Long> allProjectIdsForUser = projectService.findAllProjectForUser(userId).stream()
                .map(Project::getId)
                .toList();

        List<Message> allMessages = messageRepository.findAll();
        return allMessages.stream()
                .filter(message -> (allProjectIdsForUser.stream().anyMatch(projectId -> message.getProjectId().equals(projectId))))
                .sorted(Comparator.comparing(Message::getMessageId).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public Message saveAndGetReadyMessage(MessageDTO messageDTO, String update) {
        Message message = createMessageEntity(messageDTO, update);
        messageRepository.save(message);
        return message;
    }

    public String defineMessageText(ModificationType modificationType, String taskName, String projectName, String userEmail, String progress) {
        return switch (modificationType) {
            case UPDATE -> createUpdateMessage(taskName, projectName, userEmail);
            case UPDATE_PROGRESS -> createUpdateProgressMessage(taskName, projectName, userEmail, progress);
            default -> throw new IllegalArgumentException("The following modification does not exist");
        };
    }

    public Message createMessageEntity(MessageDTO messageDTO, String update) {
        Long taskId = messageDTO.getTaskId();
        Long userId = messageDTO.getUserId();
        Task task = taskService.findTask(taskId);
        String taskName = task.getName();
        String progress = task.getProgress();
        Long projectId = task.getProject().getId();
        String projectName = task.getProject().getName();
        String userEmail = userService.findUserById(userId).getEmail();
        String innerMessageText = defineMessageText(ModificationType.valueOf(update), taskName, projectName, userEmail, progress);
        return Message.builder()
                .taskId(taskId)
                .projectId(projectId)
                .message(innerMessageText)
                .userId(userId)
                .build();
    }

    public String createUpdateMessage(String taskName, String projectName, String userEmail) {
        return String.format("User %s modified task (%s) in project %s", userEmail, taskName, projectName);
    }

    public String createUpdateProgressMessage(String taskName, String projectName, String userEmail, String progress) {
        return String.format("In project %s, User %s changed status in task %s to %s", projectName, userEmail, taskName, progress);
    }

    public Set<Long> getAssignedUsersToProject(MessageDTO messageDTO) {
        Long taskId = messageDTO.getTaskId();
        Long projectId = taskService.getProjectIdFromTask(taskId);
        return projectService.findProjectById(projectId).getUsers().stream()
                .map(User::getIdUser)
                .collect(Collectors.toSet());
    }
}
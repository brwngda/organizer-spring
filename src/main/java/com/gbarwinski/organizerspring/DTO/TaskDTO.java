package com.gbarwinski.organizerspring.DTO;

import com.gbarwinski.organizerspring.model.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long idTask;

    @NotNull(message = "The task name cannot be empty")
    @Size(max = 20, message = "The task name is too long")
    private String name;

    @Size(max = 250, message = "The description is too long")
    private String description;
    private Sprint sprint;
    private Project project;
    private Priority priority;
    private Long storyPoints;
    private TypeOfStory typeOfStory;
    private String progress;
    private User user;

}
package com.gbarwinski.organizerspring.DTO;

import com.gbarwinski.organizerspring.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long idTask;
    private String name;
    private String description;
    private Sprint sprint;
    private Project project;
    private Priority priority;
    private Long storyPoints;
    private TypeOfStory typeOfStory;
    private String progress;
    private User user;

}
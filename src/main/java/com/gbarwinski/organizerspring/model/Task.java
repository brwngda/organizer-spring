package com.gbarwinski.organizerspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTask;
    private String name;
    private String description;

    @ManyToOne
    @JsonIgnore
    private Sprint sprint;

    @ManyToOne()
    @JsonIgnore
    private Project project;

    @Enumerated
    private Priority priority;
    private Long storyPoints;

    @Enumerated
    private TypeOfStory typeOfStory;
    private String progress;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
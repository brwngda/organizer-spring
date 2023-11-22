package com.gbarwinski.organizerspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

@Data
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
    private Sprint sprint;

    @ManyToOne
    private Project project;

    @Enumerated
    private Priority priority;
    private Long storyPoints;

    @Enumerated
    private TypeOfStory typeOfStory;
    private String progress;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
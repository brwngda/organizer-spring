package com.gbarwinski.organizerspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSprint;
    private String name;
    private Date start;
    private Date end;
    private Long storyPointsGranted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private List<Task> tasks;
}

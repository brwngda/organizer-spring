package com.gbarwinski.organizerspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sprintId;

    private String name;

    @Temporal(value = TemporalType.DATE)
    private Date start;

    @Temporal(value = TemporalType.DATE)
    private Date end;

    private Long storyPointsGranted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    @JsonIgnore
    private List<Task> tasks;
}

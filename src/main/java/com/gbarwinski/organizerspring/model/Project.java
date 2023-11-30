package com.gbarwinski.organizerspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String admin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;
    private String avatar;
    private boolean isStarred = false;

    public Project(String name, String description,String avatar,List<User> users,String admin) {
        this.name=name;
        this.description=description;
        this.avatar= avatar;
        this.users=users;
        this.admin=admin;
    }
}
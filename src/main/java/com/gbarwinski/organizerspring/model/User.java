package com.gbarwinski.organizerspring.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
    private String surname;
    private String nick;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects;

    @OneToOne(mappedBy = "user")
    private Task task;
    private String href;

    @Enumerated
    public Roles role;
}
package com.gbarwinski.organizerspring.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private String avatar;
    private String admin;
    private boolean isStarred;
}
package com.gbarwinski.organizerspring.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotNull(message = "The project name cannot be empty")
    @Size(min = 2, message = "The project name is too short")
    @Size(max = 20, message = "The project name is too long")
    private String name;

    @Size(max = 25, message = "The description is too long")
    private String description;

    @NotBlank(message = "Please select an avatar")
    private String avatar;
    private String admin;
    private boolean isStarred;
}
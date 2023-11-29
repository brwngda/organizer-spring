package com.gbarwinski.organizerspring.DTO;

import com.gbarwinski.organizerspring.authentication.validator.PasswordMatches;
import com.gbarwinski.organizerspring.authentication.validator.ValidPassword;
import com.gbarwinski.organizerspring.model.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDTO {

    @NotBlank(message = "The name cannot be empty")
    @Size(max = 30, message = "The name is too long")
    @Pattern(regexp = "[a-zA-Z]+", message = "Please use only letters")
    private String firstName;

    @NotBlank(message = "The last name cannot be empty")
    @Size(max = 30, message = "The last name is too long")
    @Pattern(regexp = "[a-zA-Z]+", message = "Please use only letters")
    private String secondName;

    @NotBlank(message = "The nick cannot be empty")
    @Size(max = 30, message = "The nick is too long")
    private String nick;

    @ValidPassword(message = "The password does not meet the requirements")
    @NotNull(message = "The password cannot be empty")
    private String password;

    @NotNull(message = "The password cannot be empty")
    private String matchingPassword;

    @NotBlank(message = "The email address cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(unique = true)
    private String email;
    private Roles roles;
}
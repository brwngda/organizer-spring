package com.gbarwinski.organizerspring.DTO;

import com.gbarwinski.organizerspring.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;
    private String secondName;
    private String nick;
    private String password;
    private String matchingPassword;
    private String email;
    private Roles roles;
}
package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.DTO.UserDTO;
import com.gbarwinski.organizerspring.model.User;

import java.io.IOException;

public interface IUserService {

    User registerNewUserAccount(UserDTO accountDto)
            throws IOException;
}
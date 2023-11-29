package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.model.User;
import com.gbarwinski.organizerspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getInitialLetters(User user) {
        String initials = user.getName().toUpperCase().substring(0, 1) + user.getSurname().toUpperCase().substring(0, 1);
        return initials;
    }
}

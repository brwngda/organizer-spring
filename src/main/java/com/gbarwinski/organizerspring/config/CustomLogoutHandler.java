package com.gbarwinski.organizerspring.config;

import com.gbarwinski.organizerspring.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("appUser");
        if (user != null) {
            log.info("User " + user.getEmail() + " was logged out");
        }
    }
}
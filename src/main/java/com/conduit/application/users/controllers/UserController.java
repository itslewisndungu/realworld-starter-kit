package com.conduit.application.users.controllers;


import com.conduit.application.users.response.UserResponse;
import com.conduit.application.users.services.AuthService;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    @GetMapping
    public UserResponse getCurrentUser(User user) {
        return new UserResponse(user);
    }
}

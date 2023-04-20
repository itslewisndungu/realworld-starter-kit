package com.conduit.application.users.controllers;


import com.conduit.application.users.requests.UpdateUserRequest;
import com.conduit.application.users.response.UserResponse;
import com.conduit.application.users.services.AuthService;
import com.conduit.application.users.services.UserService;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    @GetMapping
    public UserResponse getCurrentUser(User user) {
        return new UserResponse(user);
    }

    @PutMapping
    public UserResponse updateUserDetails(User me, @RequestBody UpdateUserRequest req) {
        var updatedUser = this.userService.updateUserInformation(me, req);
        return new UserResponse(updatedUser);
    }
}

package com.conduit.application.auth;

import com.conduit.application.auth.requests.SigninRequest;
import com.conduit.application.auth.response.UserResponse;
import com.conduit.application.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/token")
    public UserResponse token(@RequestBody SigninRequest request) {
        var user =  this.authService.signIn(request);
        return new UserResponse(user);
    }
}

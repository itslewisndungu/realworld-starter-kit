package com.conduit.application.users.controllers;

import com.conduit.application.users.requests.SignInRequest;
import com.conduit.application.users.requests.SignUpRequest;
import com.conduit.application.users.response.UserResponse;
import com.conduit.application.users.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final AuthService authService;

    @PostMapping("/login")
    public UserResponse getToken(@Valid @RequestBody SignInRequest req) {
        var user =  authService.signIn(req);
        return new UserResponse(user);
    }


    @PostMapping
    public ModelAndView signUp(@RequestBody SignUpRequest req, HttpServletRequest httpServletRequest) {
        var user = authService.signUp(req);
        httpServletRequest.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        return new ModelAndView(
                "redirect:/users/login",
                "user",
                new SignInRequest(user.getEmail(), user.getPassword())
        );
    }
}

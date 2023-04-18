package com.conduit.application.auth;

import com.conduit.application.auth.requests.SignInRequest;
import com.conduit.application.auth.requests.SignUpRequest;
import com.conduit.application.auth.response.UserResponse;
import com.conduit.application.auth.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public UserResponse getToken(@RequestBody SignInRequest request) {
        var user =  this.authService.signIn(request);
        return new UserResponse(user);
    }


    @PostMapping ("/users")
    public ModelAndView signUp(@RequestBody SignUpRequest request, HttpServletRequest httpServletRequest) {
        var user = this.authService.signUp(request);
        httpServletRequest.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        return new ModelAndView(
                "redirect:/login",
                "user",
                new SignInRequest(user.getEmail(), user.getPassword())
        );
    }
}

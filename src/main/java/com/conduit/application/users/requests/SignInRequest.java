package com.conduit.application.users.requests;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@JsonRootName("user")
//public record SignInRequest(@Email String email, @NotNull String password) {
//}

@JsonRootName("user")
@AllArgsConstructor
@Getter
public class SignInRequest {
    @Email String email;
    @NotNull String password;
}

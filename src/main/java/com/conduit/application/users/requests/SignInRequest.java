package com.conduit.application.users.requests;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@JsonRootName("user")
public record SignInRequest(@Email String email, @NotNull String password) {
}

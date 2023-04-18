package com.conduit.application.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(@Email String email, @NotNull String password) {
}

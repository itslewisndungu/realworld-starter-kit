package com.conduit.application.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SigninRequest(
        @Email
        String email,
        @NotNull
        String password) {
}

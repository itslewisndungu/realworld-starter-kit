package com.conduit.application.auth.requests;

public record SignUpRequest(String username, String email, String password) {
}

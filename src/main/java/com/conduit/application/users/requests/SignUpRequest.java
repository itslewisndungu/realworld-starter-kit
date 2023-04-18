package com.conduit.application.users.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public record SignUpRequest(String username, String email, String password) {
}

package com.conduit.application.users.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public record UpdateUserRequest(
        String email,
        String username,
        String image,
        String bio
) {
}

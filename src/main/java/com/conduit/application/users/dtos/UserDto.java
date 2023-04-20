package com.conduit.application.users.dtos;

import com.conduit.domain.user.User;

public record UserDto(String email, String username, String bio, String token, String image) {
    public UserDto(User user) {
        this(
                user.getEmail(),
                user.getUsername(),
                user.getBio(),
                user.getToken(),
                user.getImage()
        );
    }
}

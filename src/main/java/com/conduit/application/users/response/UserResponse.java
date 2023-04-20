package com.conduit.application.users.response;

import com.conduit.application.users.dtos.UserDto;
import com.conduit.domain.user.User;

public record UserResponse(UserDto user) {
    public UserResponse(User user) {
        this(new UserDto(user));
    }
}

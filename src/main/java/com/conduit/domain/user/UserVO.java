package com.conduit.domain.user;


public record UserVO(String email, String username, String bio, String token, String image) {
    public UserVO(User user) {
        this(user.email(), user.username(), user.bio(), user.token(), user.image());
    }
}

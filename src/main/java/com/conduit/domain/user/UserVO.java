package com.conduit.domain.user;


public record UserVO(String email, String username, String bio, String token, String image) {
    public UserVO(User user) {
        this(user.getEmail(), user.getUsername(), user.getBio(), user.getToken(), user.getImage());
    }
}

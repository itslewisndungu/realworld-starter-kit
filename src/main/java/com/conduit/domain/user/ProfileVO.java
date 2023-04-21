package com.conduit.domain.user;

public record ProfileVO(String username, String bio, String image, Boolean following) {
    public ProfileVO(User user, User loggedInUser) {
        this(user.getUsername(), user.getBio(), user.getImage(), loggedInUser.getFollowing().contains(user));
    }

    public ProfileVO(User user) {
        this(user.getUsername(), user.getBio(), user.getImage(), null);
    }
}

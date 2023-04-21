package com.conduit.domain.user;

public record ProfileVO(String username, String bio, String image, Boolean following) {
    public ProfileVO(User user, User loggedInUser) {
        this(user.username(), user.bio(), user.image(), loggedInUser.isFollowing(user));
    }

    public ProfileVO(User user) {
        this(user.username(), user.bio(), user.image(), null);
    }
}

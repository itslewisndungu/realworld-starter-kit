package com.conduit.application.profile;

import com.conduit.domain.user.ProfileVO;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public ProfileVO getProfile(String username, User loggedInUser) {
        return userRepository
                .findByUsername(username)
                .map(u -> loggedInUser == null ? new ProfileVO(u) : new ProfileVO(u, loggedInUser))
                .orElseThrow(
                        () -> new NoSuchElementException("User with the username %s not found".formatted(username))
                );
    }

    public void followUser(User me, String userToFollowUsername) {

    }

}

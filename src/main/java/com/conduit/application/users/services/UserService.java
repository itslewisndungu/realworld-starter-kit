package com.conduit.application.users.services;

import com.conduit.application.users.requests.UpdateUserRequest;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import com.conduit.domain.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserVO updateUserInformation(User user, UpdateUserRequest req) {
        this.updateUserEmail(user, req);
        this.updateUserUsername(user, req);
        this.updateUserBio(user, req);
        this.updateUserImage(user, req);

        var updatedUser = repository.save(user);
        return new UserVO(updatedUser);
    }

    private void updateUserEmail(User user, UpdateUserRequest req) {
        String email = req.email();

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid email address");
        }

        if (!user.email().equals(email) && repository.existsByEmail(email)) {
            throw new IllegalArgumentException("The email %s is already taken".formatted(email));
        }

        user.email(email);
    }

    private void updateUserUsername(User user, UpdateUserRequest req) {
        String username = req.username();

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid username");
        }

        if (!user.username().equals(username) && repository.existsByUsername(username)) {
            throw new IllegalArgumentException("The username %s is already taken".formatted(username));
        }

        user.username(username);
    }

    private void updateUserPassword(User user, String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Provide a valid password");
        }

        user.password(passwordEncoder.encode(password));
    }

    private void updateUserBio(User user, UpdateUserRequest req) {
        user.bio(req.bio());
    }


    private void updateUserImage(User user, UpdateUserRequest req) {
        user.image(req.image());
    }
}

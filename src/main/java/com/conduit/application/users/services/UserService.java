package com.conduit.application.users.services;

import com.conduit.application.users.requests.UpdateUserRequest;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User updateUserInformation(String email, UpdateUserRequest req) {
        var user = this.repository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with the email %s could not be found".formatted(email))
                );

        return this.updateUserInformation(user, req);
    }

    public User updateUserInformation(User user, UpdateUserRequest req) {
        this.updateUserEmail(user, req);
        this.updateUserUsername(user, req);
        this.updateUserBio(user, req);
        this.updateUserImage(user, req);

        return repository.save(user);
    }

    private void updateUserEmail(User user, UpdateUserRequest req) {
        String email = req.email();

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid email address");
        }

        if (!user.getEmail().equals(email) && repository.existsByEmail(email)) {
            throw new IllegalArgumentException("The email %s is already taken".formatted(email));
        }

        user.setEmail(email);
    }

    private void updateUserUsername(User user, UpdateUserRequest req) {
        String username = req.username();

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid username");
        }

        if (!user.getUsername().equals(username) && repository.existsByUsername(username)) {
            throw new IllegalArgumentException("The username %s is already taken".formatted(username));
        }

        user.setUsername(username);
    }

    private void updateUserPassword(User user, String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Provide a valid password");
        }

        user.setPassword(passwordEncoder.encode(password));
    }

    private void updateUserBio(User user, UpdateUserRequest req) {
        user.setBio(req.bio());
    }

    private void updateUserImage(User user, UpdateUserRequest req) {
        user.setImage(req.image());
    }
}

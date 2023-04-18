package com.conduit.application.auth.services;

import com.conduit.application.auth.dtos.UserDto;
import com.conduit.application.auth.requests.SignInRequest;
import com.conduit.application.auth.requests.SignUpRequest;
import com.conduit.domain.user.AuthorityRepository;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserDto signIn(SignInRequest request) {
        return userRepository
                .findByEmail(request.email())
                .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                .map(
                        user -> {
                            String token = jwtService.generateToken(user);
                            user.setToken(token);
                            return new UserDto(user);
                        }
                )
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }

    public User signUp(SignUpRequest request) {
        String username = request.username().strip();
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username `%s` already exists.".formatted(username));
        }

        String email = request.email().strip().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email `%s` already exists.".formatted(email));
        }

        // Create a new user
        var user = User
                .builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(request.password()))
                .build();

        return userRepository.save(user);

    }
}

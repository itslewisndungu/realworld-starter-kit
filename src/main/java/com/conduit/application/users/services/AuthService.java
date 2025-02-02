package com.conduit.application.users.services;

import com.conduit.application.users.requests.SignInRequest;
import com.conduit.application.users.requests.SignUpRequest;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import com.conduit.domain.user.UserVO;
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

    public UserVO signIn(SignInRequest request) {
        return userRepository
                .findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.password()))
                .map(
                        user -> {
                            String token = jwtService.generateToken(user);
                            user.token(token);
                            return new UserVO(user);
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

        var user = new User()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}

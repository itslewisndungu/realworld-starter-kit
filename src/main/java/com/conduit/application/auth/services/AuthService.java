package com.conduit.application.auth.services;

import com.conduit.application.auth.requests.SigninRequest;
import com.conduit.application.auth.dtos.UserDto;
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
    private final PasswordEncoder encoder;

    public UserDto signIn(SigninRequest request) {
        return userRepository
                .findByEmail(request.email())
                .filter(user -> encoder.matches(request.password(), user.getPassword()))
                .map(
                        user -> {
                            String token = jwtService.generateToken(user);
                            user.setToken(token);
                            return new UserDto(user);
                        }
                )
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }
}

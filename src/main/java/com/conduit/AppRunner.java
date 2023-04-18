package com.conduit;

import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("running the command line runner");
        this.generateUser();
    }

    private void generateUser() {
        var user = User
                .builder()
                .email("lewis@gmail.com")
                .username("lewiclancy")
                .bio("long live the fucking king")
                .image("https://localhost:3000")
                .password(passwordEncoder.encode("9326"))
                .build();

        userRepository.save(user);
    }
}

package com.conduit.config;

import com.conduit.application.users.Resolvers.UserArgumentResolver;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final UserRepository userRepository;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        var userResolver = new UserArgumentResolver(userRepository);
        resolvers.add(userResolver);
    }
}

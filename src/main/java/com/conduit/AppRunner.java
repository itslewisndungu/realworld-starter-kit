package com.conduit;

import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
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
    private final ArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("running the command line runner");
        var user = this.generateUser();
        this.generateArticle(user);
    }

    private User generateUser() {
        var user = new User()
                .email("lewis@gmail.com")
                .username("lewiclancy")
                .bio("long live the king")
                .image("https://localhost:3000")
                .password(passwordEncoder.encode("9326"));

        return userRepository.save(user);
    }

    private Article generateArticle(User author) {
        var article = new Article()
                .author(author)
                .slug("new-article")
                .title("New Article")
                .description("New generated article")
                .body("This is the generated article");

        return articleRepository.save(article);
    }
}

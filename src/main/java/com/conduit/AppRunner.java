package com.conduit;

import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
Vimport com.conduit.domain.content.Tag;
import com.conduit.domain.content.TagRepository;
import com.conduit.domain.user.User;
import com.conduit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("running the command line runner");
        this.generateData();
    }

    private void generateData() {
        var user1 = new User()
                .email("lewis@gmail.com")
                .username("lewiclancy")
                .bio("long live the king")
                .image("https://localhost:3000")
                .password(passwordEncoder.encode("9326"));

        var user2 = new User()
                .email("lewisn@gmail.com")
                .username("lewii")
                .bio("long live the king")
                .image("https://localhost:3000")
                .password(passwordEncoder.encode("9326"));

         userRepository.save(user1);
         userRepository.save(user2);

        user1.follow(user2);
        userRepository.save(user1);

        this.generateArticle(user2);
    }

    private void generateArticle(User author) {
        var tag1 = new Tag().name("react");
        var tag2 = new Tag().name("angular");

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        var article1 = new Article()
                .author(author)
                .slug("hello-world")
                .title("New World")
                .description("New world generated article")
                .body("This is the new generated article")
                .tags(Set.of(tag1));

        var article2 = new Article()
                .author(author)
                .slug("new-article")
                .title("New Article")
                .description("New generated article")
                .body("This is the generated article")
                .tags(Set.of(tag2));


        articleRepository.save(article1);
        articleRepository.save(article2);
    }
}

package com.conduit.application.articles;


import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizedArticleService {
    private final ArticleRepository repository;

    @PreAuthorize("authentication.getName == #article.author.email")
    public void delete(Article article) {
        this.repository.delete(article);
    }

    @PreAuthorize("authentication.getName == #article.author.email")
    public Article update(Article article) {
        return this.repository.save(article);
    }

}

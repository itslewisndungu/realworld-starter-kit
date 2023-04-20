package com.conduit.application.article.services;

import com.conduit.application.article.dtos.ArticleDto;
import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public List<Article> retrieveAllArticles() {
        return this.repository.findAll();
    }

    public Article getArticle(String slug) {
        return this.repository.getArticleBySlug(slug).orElseThrow(
                () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
        );
    }

    public Article createNewArticle(ArticleDto article, User author) {
        Article newArticle = new Article()
                .author(author)
                .slug(article.getSlug())
                .title(article.getTitle())
                .description(article.getDescription())
                .body(article.getBody());

        return this.repository.save(newArticle);
    }

    public Article updateArticle(ArticleDto article, String slug) {
        Article oldArticle = this.repository.getArticleBySlug(slug).orElseThrow(
                () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
        );

        oldArticle.title(article.getTitle());
        oldArticle.body(article.getBody());
        oldArticle.description(article.getDescription());

        return this.repository.save(oldArticle);
    }

    public void deleteArticle(String slug) {
        Article article = this.repository.getArticleBySlug(slug).orElseThrow(
                () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
        );
        this.repository.delete(article);
    }
}

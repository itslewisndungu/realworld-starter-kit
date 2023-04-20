package com.conduit.application.article.controllers;

import com.conduit.application.article.services.ArticleService;
import com.conduit.application.article.dtos.ArticleDto;
import com.conduit.domain.content.Article;
import com.conduit.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return this.articleService.retrieveAllArticles();
    }

    @GetMapping("/{slug}")
    public Article getArticle(@PathVariable("slug") String slug) {
        return this.articleService.getArticle(slug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(User author, @Valid @RequestBody ArticleDto article) {
        return this.articleService.createNewArticle(article, author);
    }

    @PutMapping("/{slug}") public Article updateArticle(
            @Valid @RequestBody ArticleDto article,
            @PathVariable("slug") String slug
    ) {
        return this.articleService.updateArticle(article, slug);
    }

    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteArticle(@PathVariable String slug) {
        this.articleService.deleteArticle(slug);
    }
}

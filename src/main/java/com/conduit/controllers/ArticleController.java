package com.conduit.controllers;

import com.conduit.dto.ArticleDto;
import com.conduit.models.Article;
import com.conduit.services.ArticleService;
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
    public Article createArticle(@Valid @RequestBody ArticleDto article) {
        return this.articleService.createNewArticle(article);
    }

    @PutMapping("/{slug}")
    public Article updateArticle(
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

package com.conduit.application.article.controllers;

import com.conduit.application.article.requests.CreateArticleRequest;
import com.conduit.application.article.requests.UpdateArticleRequest;
import com.conduit.application.article.response.ArticleResponse;
import com.conduit.application.article.response.MultipleArticlesResponse;
import com.conduit.application.article.services.ArticleService;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public MultipleArticlesResponse getAllArticles(User user) {
        var articles = this.articleService.retrieveAllArticles(user);
        return new MultipleArticlesResponse(articles);
    }

    @GetMapping("/{slug}")
    public ArticleResponse getArticle(@PathVariable("slug") String slug, User user) {
        var article = this.articleService.getArticle(slug, user);
        return new ArticleResponse(article);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse createArticle(@RequestBody CreateArticleRequest request, User author) {
        var createdArticle = this.articleService.createNewArticle(request, author);
        return new ArticleResponse(createdArticle);
    }

    @PutMapping("/{slug}")
    public ArticleResponse updateArticle(
            @RequestBody UpdateArticleRequest request,
            @PathVariable("slug") String slug,
            User user
    ) {
        var updatedArticle = this.articleService.updateArticle(request, slug, user);
        return new ArticleResponse(updatedArticle);
    }

    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable String slug) {
        this.articleService.deleteArticle(slug);
    }
}

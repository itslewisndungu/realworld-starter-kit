package com.conduit.application.articles;

import com.conduit.application.articles.requests.ArticleFacets;
import com.conduit.application.articles.requests.CreateArticleRequest;
import com.conduit.application.articles.requests.UpdateArticleRequest;
import com.conduit.application.articles.response.ArticleResponse;
import com.conduit.application.articles.response.MultipleArticlesResponse;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public MultipleArticlesResponse getAllArticles(
            User user,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset
    ) {
        var facets = new ArticleFacets(author, tag, offset, limit);
        var articles = this.articleService.retrieveAllArticles(user, facets);
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

    @DeleteMapping("/{slug}/favorite")
    public ArticleResponse unfavoriteArticle(@PathVariable String slug, User user) {
        var article = this.articleService.favoriteOrUnfavoriteArticle(slug, user);
        return new ArticleResponse(article);
    }

    @PostMapping("/{slug}/favorite")
    public ArticleResponse favoriteArticle(@PathVariable String slug, User user) {
        var article = this.articleService.favoriteOrUnfavoriteArticle(slug, user);
        return new ArticleResponse(article);
    }
}

package com.conduit.application.article.services;

import com.conduit.domain.content.ArticleVO;
import com.conduit.application.article.requests.CreateArticleRequest;
import com.conduit.application.article.requests.UpdateArticleRequest;
import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public List<ArticleVO> retrieveAllArticles(User user, int offset, int limit) {
        var pageable = PageRequest.of(offset, limit);

        return this.repository
                .findAll(pageable)
                .stream()
                .map(a -> new ArticleVO(a, user))
                .toList();
    }

    public ArticleVO getArticle(String slug, User user) {
        return this.repository
                .getArticleBySlug(slug)
                .map(a -> new ArticleVO(a, user))
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
                );
    }

    public ArticleVO createNewArticle(CreateArticleRequest request, User author) {
        var newArticle = new Article()
                .author(author)
                .slug(request.slug())
                .title(request.title())
                .description(request.description())
                .body(request.body());

        var savedArticle = this.repository.save(newArticle);
        return new ArticleVO(savedArticle, null);
    }

    public ArticleVO updateArticle(UpdateArticleRequest request, String slug, User user) {
        Article oldArticle = this.repository
                .getArticleBySlug(slug)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
                );

        oldArticle.title(request.title());
        oldArticle.body(request.body());
        oldArticle.description(request.description());

        var updatedArticle =  this.repository.save(oldArticle);
        return new ArticleVO(updatedArticle, null);
    }

    public void deleteArticle(String slug) {
        Article article = this.repository.getArticleBySlug(slug).orElseThrow(
                () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
        );
        this.repository.delete(article);
    }
}

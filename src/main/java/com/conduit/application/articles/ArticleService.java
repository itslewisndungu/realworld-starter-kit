package com.conduit.application.articles;

import com.conduit.application.articles.requests.ArticleFacets;
import com.conduit.application.articles.requests.CreateArticleRequest;
import com.conduit.application.articles.requests.UpdateArticleRequest;
import com.conduit.application.tags.TagService;
import com.conduit.domain.content.Article;
import com.conduit.domain.content.ArticleRepository;
import com.conduit.domain.content.ArticleVO;
import com.conduit.domain.content.Tag;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;
    private final TagService tagService;
    private final AuthorizedArticleService authorizedArticleService;


    @PreAuthorize("authentication.getName == #name")
    public void testAuth(String name) {
        System.out.println(name);
    }


    public List<ArticleVO> retrieveAllArticles(User user, ArticleFacets facets) {
        var pageable = PageRequest.of(facets.offset(), facets.limit());

        return this.repository
                .findAllByFacets(
                        facets.author(), facets.tag(), pageable
                )
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
        Set<Tag> tags = new HashSet<>();

        if (request.tagsList().isPresent()) {
            tags = request.tagsList().get()
                    .stream()
                    .map(tagService::findOrCreateTagByName)
                    .collect(Collectors.toSet());
        }

        var newArticle = new Article()
                .author(author)
                .slug(request.slug())
                .title(request.title())
                .description(request.description())
                .body(request.body())
                .tagsList(tags);

        var savedArticle = this.repository.save(newArticle);
        return new ArticleVO(savedArticle, null);
    }

    public ArticleVO updateArticle(UpdateArticleRequest request, String slug, User user) {
        Article oldArticle = this.repository
                .getArticleBySlug(slug)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
                );

        if (request.title().isPresent()) {
            oldArticle.title(request.title().get());
        }

        if (request.body().isPresent()) {
            oldArticle.body(request.body().get());
        }

        if (request.description().isPresent()) {
            oldArticle.description(request.description().get());
        }

        var updatedArticle = this.authorizedArticleService.update(oldArticle);
        return new ArticleVO(updatedArticle, user);
    }


    public void deleteArticle(String slug) {
        Article article = this.repository.getArticleBySlug(slug).orElseThrow(
                () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
        );

        this.authorizedArticleService.delete(article);
    }

    public ArticleVO favoriteOrUnfavoriteArticle(String slug, User me) {
        var article = repository.getArticleBySlug(slug)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Article with the slug %s not found", slug))
                );

        article.favoriteOrUnfavoriteArticle(me);
        article = this.repository.save(article);

        return new ArticleVO(article, me);
    }
}

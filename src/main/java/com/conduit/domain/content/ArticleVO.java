package com.conduit.domain.content;


import com.conduit.domain.user.ProfileVO;
import com.conduit.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleVO(
        String slug,
        String title,
        String description,
        String body,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> tagsList,
        ProfileVO author,
        int favouritesCount,
        boolean favourited
) {
    public ArticleVO(Article article, User user) {
        this(
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                article.createdAt(),
                article.updatedAt(),
                article.tagsList().stream().map(Tag::name).toList(),
                new ProfileVO(article.author(), user),
                article.favourites().size(),
                article.favourites().contains(user)
        );
    }
}

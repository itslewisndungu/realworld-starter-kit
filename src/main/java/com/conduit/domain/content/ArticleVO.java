package com.conduit.domain.content;


import com.conduit.domain.user.ProfileVO;
import com.conduit.domain.user.User;

import java.time.LocalDateTime;

public record ArticleVO(
        String slug,
        String title,
        String description,
        String body,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ProfileVO author
) {
    public ArticleVO(Article article, User user) {
        this(
                article.slug(),
                article.title(),
                article.description(),
                article.body(),
                article.createdAt(),
                article.updatedAt(),
                new ProfileVO(article.author(), user)
        );
    }
}
/*
{
  "article": {
    "slug": "how-to-train-your-dragon",
    "title": "how to train your dragon",
    "description": "ever wonder how?",
    "body": "it takes a jacobian",
    "taglist": ["dragons", "training"],
    "createdat": "2016-02-18t03:22:56.637z",
    "updatedat": "2016-02-18t03:48:35.824z",
    "favorited": false,
    "favoritescount": 0,
    "author": {
      "username": "jake",
      "bio": "I work at statefarm",
      "image": "https://i.stack.imgur.com/xHWG8.jpg",
      "following": false
    }
  }
}
*/

package com.conduit.application.article.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;
import java.util.Set;


@JsonRootName("article")
public record CreateArticleRequest(
        String title, String slug, String description, String body, Optional<Set<String>> tagsList
) {
}

package com.conduit.application.article.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("article")
public record CreateArticleRequest(String title, String slug, String description, String body) {
}

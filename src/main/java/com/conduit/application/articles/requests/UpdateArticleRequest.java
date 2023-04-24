package com.conduit.application.articles.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("article")
public record UpdateArticleRequest(String title, String description, String body) {
}

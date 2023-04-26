package com.conduit.application.articles.requests;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Optional;

@JsonRootName("article")
public record UpdateArticleRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> body) {
}

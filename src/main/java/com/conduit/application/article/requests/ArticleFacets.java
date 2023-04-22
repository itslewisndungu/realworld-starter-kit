package com.conduit.application.article.requests;

public record ArticleFacets(String author, String tag, int offset, int limit) {
}

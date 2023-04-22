package com.conduit.application.articles.requests;

public record ArticleFacets(String author, String tag, int offset, int limit) {
}

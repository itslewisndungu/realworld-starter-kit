package com.conduit.application.article.response;

import com.conduit.domain.content.ArticleVO;

import java.util.List;

public record MultipleArticlesResponse(List<ArticleVO> articles, int articlesCount) {
    public MultipleArticlesResponse(List<ArticleVO> articles) {
        this(articles, articles.size());
    }
}

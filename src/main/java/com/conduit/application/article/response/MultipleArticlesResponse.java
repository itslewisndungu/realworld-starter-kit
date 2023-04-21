package com.conduit.application.article.response;

import com.conduit.domain.content.ArticleVO;

import java.util.List;

public record MultipleArticlesResponse(List<ArticleVO> articles) {
}

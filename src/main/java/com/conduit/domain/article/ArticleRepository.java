package com.conduit.domain.article;

import com.conduit.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> getArticleBySlug(String slug);
}
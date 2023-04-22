package com.conduit.domain.content;

import com.conduit.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> getArticleBySlug(String slug);

    Page<Article> findAllByAuthor(User author, Pageable pageable);
}

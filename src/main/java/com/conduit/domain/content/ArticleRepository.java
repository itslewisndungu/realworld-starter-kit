package com.conduit.domain.content;

import com.conduit.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> getArticleBySlug(String slug);

    Page<Article> findAllByAuthor(User author, Pageable pageable);

    @Query("""
            select a from Article a
            where (:author IS NULL OR a.author.username = :author)
            and (:tag is null or :tag in (select t.name from a.tagsList t))
            """)
    Page<Article> findAllByFacets(
            @Param("author") String author,
            @Param("tag") String tag,
            Pageable pageable
    );

    Page<Article> findByAuthorInOrderByCreatedAt(Collection<User> author, Pageable pageable);
}

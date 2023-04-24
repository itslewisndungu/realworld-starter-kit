package com.conduit.application.comments;

import com.conduit.domain.content.*;
import com.conduit.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<CommentVO> getCommentsFromAnArticle(String articleSlug, User me) {
        var comments = this.articleRepository
                .getArticleBySlug(articleSlug)
                .map(Article::comments)
                .orElseThrow(
                        () -> new NoSuchElementException("Article with the slug %s not found".formatted(articleSlug))
                );

        return comments.stream().map(c -> new CommentVO(c, me)).toList();
    }

    public CommentVO createComment(String articleSlug, CreateCommentRequest request, User author) {
        var article = this.articleRepository
                .getArticleBySlug(articleSlug)
                .orElseThrow(
                        () -> new NoSuchElementException("Article with the slug %s not found".formatted(articleSlug))
                );

        Comment comment = new Comment()
                .article(article)
                .body(request.body())
                .author(author);

        comment = this.commentRepository.save(comment);

        return new CommentVO(comment, author);
    }

    @PreAuthorize("#author")
    public void deleteComment(Long commentId, String articleSlug, User author) {
        var articleComments = this.articleRepository
                .getArticleBySlug(articleSlug)
                .map(Article::comments)
                .orElseThrow(
                        () -> new NoSuchElementException("Article with the slug %s not found".formatted(articleSlug))
                );

        var comment = articleComments.stream()
                .filter(c -> Objects.equals(c.id(), commentId))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Comment with the id %d not found".formatted(commentId))
                );

        this.commentRepository.delete(comment);
    }
}

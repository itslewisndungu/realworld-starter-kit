package com.conduit.domain.content;

import com.conduit.domain.user.ProfileVO;
import com.conduit.domain.user.User;

import java.time.LocalDateTime;

public record CommentVO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String body, ProfileVO author) {
    public CommentVO(Comment comment, User me) {
        this(
                comment.id(),
                comment.createdAt(),
                comment.updatedAt(),
                comment.body(),
                new ProfileVO(comment.author(), me)
        );
    }
}
